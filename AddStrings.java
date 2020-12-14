import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * LeetCode 415. Add Strings https://leetcode.com/problems/add-strings/
 */
public class AddStrings {


    /**
     * Processing two character arrays.
     * 
     * Runtime: 3 ms, faster than 37.61% of Java online submissions.
     * Memory Usage: 39.3 MB, less than 26.61% of Java online submissions.
     */
    static String addStrings(String num1, String num2) {

        // **** sanity check(s) ****
        if (num1.equals("") || num1.equals("0")) {
            return num2;
        }

        if (num2 == "" || num2 == "0") {
            return num1;
        }

        // **** get length of largest string ****
        int len = Math.max(num1.length(), num2.length());

        // ???? ????
        System.out.println("<<< len: " + len);

        // **** take into account possible carry over ****
        len++;

        // ???? ????
        System.out.println("<<< len: " + len);

        // **** prepend '0' to the shortest string ****
        if (num1.length() > num2.length()) {
            while (num1.length() != num2.length())
                num2 = "0" + num2;
        } else if (num1.length() < num2.length()) {
            while (num1.length() != num2.length())
                num1 = "0" + num1;
        }

        // ???? ????
        System.out.println("<<< num1 ==>" + num1 + "<==");
        System.out.println("<<< num2 ==>" + num2 + "<==");

        // **** initialization ****
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        char[] res  = new char[len];
        int carry   = 0;

        // **** loop from right to left ****
        for (int i = len - 2; i >= 0; i--) {

            // **** compute sum of digits with same position ****
            int sum = carry;
            sum     += (arr1[i] - '0');
            sum     += (arr2[i] - '0');

            // **** update result array ****
            res[i] = (char)((sum % 10) + '0');

            // **** compute carry ****
            if (sum >= 10)
                carry = 1;
            else 
                carry = 0;

            // ???? ????
            System.out.println("<<< i: " + i + " sum: " + sum + " carry: " + carry);
        }

        // ???? ????
        System.out.println("<<< res: " + Arrays.toString(res));

        // **** ****
        StringBuilder sb = new StringBuilder();

        // **** ****
        if (carry != 0)
            sb.append(String.valueOf(carry));

        // **** ****
        sb.append(res, 0, len - 1);

        // **** return result as a string ****
        return sb.toString();
    }


    /**
     * Without the two character arrays.
     * We can avoid prepending '0's to shorter string.
     * 
     * Runtime: 2 ms, faster than 91.94% of Java online submissions.
     * Memory Usage: 39.1 MB, less than 60.40% of Java online submissions.
     */
    static String addStrings1(String num1, String num2) {

        // **** sanity check(s) ****
        if (num1.equals("") || num1.equals("0")) {
            return num2;
        }

        if (num2 == "" || num2 == "0") {
            return num1;
        }

        // **** initialization ****
        int i               = num1.length() - 1;
        int j               = num2.length() - 1;
        int carry           = 0;
        StringBuilder sb    = new StringBuilder();

        // **** traverse strings right to left ****
        while (i >= 0 || j >= 0) {

            // **** sum associated digits with carry ****
            int sum = carry;
            if (i >= 0)
                sum += num1.charAt(i) - '0';
            if (j >= 0)
                sum += num2.charAt(j) - '0';

            // **** compute carry ****
            if (sum >= 10)
                carry = 1;
            else
                carry = 0;

            // ???? ????
            System.out.println("<<< i: " + i + " j: " + j + " sum: " + sum + " carry: " + carry);

            // **** append sum digit to string builder ****
            sb.append(sum % 10);

            // **** increment indices ****
            i--; j--;
        }

        // **** append carry (if needed) ****
        if (carry != 0)
            sb.append(carry);

        // **** return reversed string of digits ****
        return sb.reverse().toString();
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read both strings ****
        String str1 = br.readLine().trim();
        String str2 = br.readLine().trim();

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< str1 ==>" + str1 + "<==");
        System.out.println("main <<< str2 ==>" + str2 + "<==");

        // **** compute and display result ****
        System.out.println("main <<< result: " + addStrings(str1, str2));

        // **** compute and display result ****
        System.out.println("main <<< result: " + addStrings1(str1, str2));
    }
}