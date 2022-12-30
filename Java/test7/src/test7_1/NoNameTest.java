package test7_1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NoNameTest
{
    @Test
    public void test1()
    {
        String trim = "  hello world   ".trim();
        System.out.println(trim);
        int maxValue = Integer.MAX_VALUE;
    }
}

class Solution {
    public int threeSumClosest(int[] nums, int target)
    {
        Arrays.sort(nums);
        int len = nums.length;
        int res = Integer.MAX_VALUE-1;
        for(int a = 0; a<len; a++)
        {
            if(a > 0 && nums[a] == nums[a-1] )
                continue;
            int b = a+1;
            int c = len-1;
            while(b<c)
            {
                int sum = nums[a] + nums[b] + nums[c];
                if(sum == target)
                    return sum;
                if(Math.abs(sum - target) < Math.abs(target - res))
                    res = sum;
                if(sum > target)
                {
                    while(nums[--c] != nums[c]);
                    continue;
                }
                if(sum < target)
                {
                    while(nums[++b] != nums[b]);
                    continue;
                }
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        int i = new Solution().threeSumClosest(new int[]{-3, -2, -5, 3, -4}, -1);
        System.out.println(i);
    }
}
