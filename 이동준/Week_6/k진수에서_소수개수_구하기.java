import java.util.*;

/**
 * k진수에서 소수개수 구하기
 * 2022 KAKAO BLIND RECRUITMENT
 * 실패 - 소수구하기에서 제곱근을 통해 구해야 하는 이유를 모르겠음.
 * 
 * 시도 상황
 * 1 : 2 -> N까지 나머지 연산으로 +1씩 false 체크
 * 2 : 2 -> N까지 나머지 연산으로 +1씩 i*i 체크
 * 3 : 2 -> Math.sqrt(N)까지 +1씩 false 체크 => 정답
 * 
 * i*i 체크로 특정 큰 수에 도달하는 것 보다 Math.sqrt(N)이 더 범위가 작아짐.
 * 
 * author djunnni
 */
class k진수에서_소수개수_구하기 {
    public int solution(int n, int k) {
        String data = conversion(n, k);

        String[] list = data.split("0");
        Arrays.sort(list);

        int answer = 0;
        for (String str : list) {
            if (str.trim().length() > 0 && isDecimal(Long.parseLong(str.trim())))
                answer++;
        }

        return answer;
    }

    public String conversion(int n, int k) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            if (n % k < 10) {
                sb.append(n % k);
            }
            n /= k;
        }

        return "" + sb.reverse();
    }

    public boolean isDecimal(long num) {
        if (num <= 1) {
            return false;
        }

        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}