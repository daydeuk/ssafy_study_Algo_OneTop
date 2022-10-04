package day_221004;

import java.util.ArrayList;

public class k진수에서_소수개수_구하기 {
    public static void main(String[] args) {
        solution(885733, 3);
    }

    public static int solution(int n, int k) {
        int answer = 0;
        String tmp = "";

        while (true) {
            if (n < k){
                tmp += n;
                break;
            }
            tmp += n % k;
            n = n / k;
        }

        char[] zin_su = tmp.toCharArray();

        String prime = "";
        ArrayList<String> list = new ArrayList<>();

        for (int i = zin_su.length - 1; i >= 0; i--) {
            if (zin_su[i] != '0') {
                prime += zin_su[i];
            } else { // 0이 아니면 validate
                list.add(prime);
                prime = "";
            }
        }

        if (prime != "") {
            list.add(prime);
        }

        for (String s : list) {
            if (validate(s)) {
                answer += 1;
            }
        }

        return answer;
    }

    public static boolean validate(String prime) {
        if (prime == "") {
            return false;
        }

        long p = Long.parseLong(prime);

        if (p == 1) {
            return false;
        }

        // 자기 자신과 1을 제외한 수로 나눠지지 않는것이 소수이다.
        for (int i = 2; i < (long)Math.sqrt(p) + 1; i++) {
            if (p % i == 0) { // 나눠지면 false
                return false;
            }
        }
        return true;
    }
}
