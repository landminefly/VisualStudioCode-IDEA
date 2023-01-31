package test11_8;

import java.util.*;

public class HuffmanCodeTest {
    public static void main(String[] args) {
        String s = "我承认前两天说话大声了点。河海大学是一所优秀的大学，师资雄厚，学习氛围积极向上，老师耐心细致，同学友好互助，";
        byte[] bytes = s.getBytes();
        System.out.println(Arrays.toString(bytes));
        HuffmanTreeForCode huffmanTree = new HuffmanTreeForCode(bytes);
        // for(Map.Entry<Byte,String> i: huffmanTree.huffmanCodes.entrySet()){
        //     System.out.println(i.getKey());
        //     System.out.println(i.getValue());
        // }
        byte[] compressed = huffmanTree.compress(bytes);
        System.out.println(Arrays.toString(compressed));
        byte[] decompressed = huffmanTree.decompress(compressed);
        System.out.println(Arrays.toString(decompressed));

        System.out.println(new String(decompressed).equals(s));
    }
}

class HuffmanTreeForCode {
    //赫夫曼树的根节点
    public NodeForCode root;
    //赫夫曼编码
    public HashMap<Byte, String> huffmanCodes;

    //根据传入的byte[]，构造赫夫曼树，随后构造赫夫曼编码
    public HuffmanTreeForCode(byte[] bytes) {
        //获取叶子节点
        ArrayList<NodeForCode> nodes = getNodes(bytes);
        //构造赫夫曼树
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            NodeForCode leftNode = nodes.get(0);
            NodeForCode rightNode = nodes.get(1);
            NodeForCode parent = new NodeForCode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(parent);
        }
        root = nodes.get(0);
        //构造赫夫曼编码
        getCodes();
    }

    //根据传入的byte[]，构造叶子节点，封装在集合中返回
    private ArrayList<NodeForCode> getNodes(byte[] bytes) {
        ArrayList<NodeForCode> nodes = new ArrayList<>();
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte i : bytes) {
            Integer count = counts.get(i);
            if (count == null) {
                counts.put(i, 1);
            } else {
                counts.put(i, count + 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new NodeForCode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //根据赫夫曼树，构造赫夫曼编码
    private void getCodes() {
        huffmanCodes = new HashMap<>();
        if (root == null) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (root.left != null) {
            //向左递归，传入0
            getCodes(root.left, "0", stringBuilder);
        }
        if (root.right != null) {
            //向右递归，传入1
            getCodes(root.right, "1", stringBuilder);
        }
    }

    /**
     * @param curNode       当前节点
     * @param curCode       根据其父节点是向左还是向右走到该节点，传入0或1
     * @param stringBuilder 记录当前节点的赫夫曼编码
     */
    private void getCodes(NodeForCode curNode, String curCode, StringBuilder stringBuilder) {
        //先将curCode加到stringBuilder中，得到该节点的赫夫曼编码
        stringBuilder.append(curCode);
        //如果还没到叶子节点，那就继续递归
        if (curNode.data == null) {
            if (curNode.left != null) {
                //向左递归，传入0
                getCodes(curNode.left, "0", stringBuilder);
            }
            if (curNode.right != null) {
                //向右递归，传入1
                getCodes(curNode.right, "1", stringBuilder);
            }
            //每向上回溯一次，都要删除stringBuilder的最后一个字符
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        } else {
            //如果到达叶子节点，此时的stringBuilder就是该节点对应byte值的赫夫曼编码
            //将其加入到huffmanCodes中
            huffmanCodes.put(curNode.data, stringBuilder.toString());
            //随后向上回溯，不要忘记删除stringBuilder的最后一个字符
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    //将01串转换成byte[]时，最后一次转换的二进制数是否满8位
    private boolean isFull;
    //如果不满8位，那么最后一个1之后是否有0（比如00100），如果有，记录0的个数
    private int zeroCount;

    //压缩
    public byte[] compress(byte[] bytes) {
        if (huffmanCodes.isEmpty()) {
            return new byte[0];
        }
        //根据赫夫曼编码，将 byte[] 的内容转换成一个01串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte i : bytes) {
            stringBuilder.append(huffmanCodes.get(i));
        }
        //计算该01串能转换成多少个byte值
        int length = (stringBuilder.length() + 7) / 8;
        byte[] compressedBytes = new byte[length];
        //将该01串，8位8位地转换成一个个byte值（若最后不足8位，则直接转换），将这些byte值依次放在compressedBytes中
        for (int bytesIndex = 0, compressedIndex = 0; bytesIndex < stringBuilder.length(); bytesIndex += 8) {
            //判断是否到达最后一次转换
            if (bytesIndex + 8 < stringBuilder.length()) {
                compressedBytes[compressedIndex++] = (byte) Integer.parseInt(stringBuilder.substring(bytesIndex, bytesIndex + 8), 2);
            } else {
                //若到达最后一次转换，要记录isFull和zeroCount的值，解压时有用
                compressedBytes[compressedIndex++] = (byte) Integer.parseInt(stringBuilder.substring(bytesIndex), 2);
                if (bytesIndex + 8 == stringBuilder.length()) {
                    isFull = true;
                } else {
                    for (int j = bytesIndex; j < stringBuilder.length() - 1 && '0' == stringBuilder.charAt(j); j++) {
                        zeroCount++;
                    }
                }
            }
        }
        return compressedBytes;
    }

    //解压
    public byte[] decompress(byte[] bytes) {
        //将bytes的内容转换成一个01串
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < bytes.length; i++) {
            if (i == bytes.length - 1) {
                if (!isFull) {
                    flag = false;
                }
            }
            stringBuilder.append(byteToString(flag, bytes[i]));
        }

        //将赫夫曼编码临时反转，方便转换
        HashMap<String, Byte> reverseHuffmanCodes = new HashMap<>();
        for (HashMap.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            reverseHuffmanCodes.put(entry.getValue(), entry.getKey());
        }

        //根据赫夫曼编码，将01串转换成一个新的 byte[]
        ArrayList<Byte> decompressed = new ArrayList<>();
        //定义两个指针，i在后，count在前
        //count一直往前走，直到i与count之间为一个有效的赫夫曼编码，将该编码转换成对应的byte值加入到集合中
        //随后i向前与count同步，继续上述循环
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            Byte b = null;
            while (true) {
                String key = stringBuilder.substring(i, i + count);
                b = reverseHuffmanCodes.get(key);
                if (b == null) {
                    count++;
                } else {
                    break;
                }
            }
            decompressed.add(b);
            i += count;
        }
        //最后将集合转换为数组并返回
        byte[] decompressedBytes = new byte[decompressed.size()];
        for (int i = 0; i < decompressed.size(); i++) {
            decompressedBytes[i] = decompressed.get(i);
        }
        return decompressedBytes;
    }

    /**
     * @param flag 当flag为true时，表示该元素在原压缩编码中的二进制数应该有8位，那么正数的位数要补到8位，负数要截取其后8位；
     *             当flag位false时，表示该元素在原压缩编码中的二进制数不满8位，缺少了第8位符号位，因此该元素必为正数，
     *             考虑到可能缺失的0（比如 0010 会被转换成 2，再转换回去就变成 10，缺少了两个0），还要配合zeroCount将0补足
     */
    private String byteToString(boolean flag, byte b) {
        int temp = b;
        //flag为true时
        //将正数的位数补到8位
        //虽然负数也会执行该操作，但不会对其产生影响
        if (flag) {
            temp |= 256;
        }
        StringBuilder str = new StringBuilder(Integer.toBinaryString(temp));
        //flag为true时
        //将负数截取其后8位
        //虽然正数也会执行该操作，但不会对其产生影响
        if (flag) {
            return str.substring(str.length() - 8);
        }
        //flag为false时，配合zeroCount将其还原成应有的位数
        else {
            while (zeroCount-- > 0) {
                str.insert(0, "0");
            }
            return str.toString();
        }
    }
}

class NodeForCode implements Comparable<NodeForCode> {
    Byte data;
    public int weight;
    public NodeForCode left;
    public NodeForCode right;

    public NodeForCode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(NodeForCode o) {
        return weight - o.weight;
    }
}