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
    public NodeForCode root;
    public HashMap<Byte, String> huffmanCodes;

    public HuffmanTreeForCode(byte[] bytes) {
        ArrayList<NodeForCode> nodes = getNodes(bytes);
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
        getCodes();
    }

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

    private void getCodes() {
        huffmanCodes = new HashMap<>();
        if (root == null) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (root.left != null) {
            getCodes(root.left, "0", stringBuilder);
        }
        if (root.right != null) {
            getCodes(root.right, "1", stringBuilder);
        }
    }

    private void getCodes(NodeForCode curNode, String curCode, StringBuilder stringBuilder) {
        stringBuilder.append(curCode);
        if (curNode.data == null) {
            if (curNode.left != null) {
                getCodes(curNode.left, "0", stringBuilder);
            }
            if (curNode.right != null) {
                getCodes(curNode.right, "1", stringBuilder);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        } else {
            huffmanCodes.put(curNode.data, stringBuilder.toString());
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    private int zeroCount;
    private boolean isFull;

    public byte[] compress(byte[] bytes) {
        if (huffmanCodes.isEmpty()) {
            return new byte[0];
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (byte i : bytes) {
            stringBuilder.append(huffmanCodes.get(i));
        }
        int length = (stringBuilder.length() + 7) / 8;
        byte[] compressedBytes = new byte[length];
        for (int bytesIndex = 0, compressedIndex = 0; bytesIndex < stringBuilder.length(); bytesIndex += 8) {
            if (bytesIndex + 8 < stringBuilder.length()) {
                compressedBytes[compressedIndex++] = (byte) Integer.parseInt(stringBuilder.substring(bytesIndex, bytesIndex + 8), 2);
            } else {
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

    public byte[] decompress(byte[] bytes) {
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

        HashMap<String, Byte> reverseHuffmanCodes = new HashMap<>();
        for (HashMap.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            reverseHuffmanCodes.put(entry.getValue(), entry.getKey());
        }

        ArrayList<Byte> decompressed = new ArrayList<>();
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