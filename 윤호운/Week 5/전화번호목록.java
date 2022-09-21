import java.util.*;
import java.io.*;



class Solution {
    public boolean solution(String[] phone_book) {
        //일단 정렬..
        Arrays.sort(phone_book);
        
        // 배열에서 뒤에있는 문자열이 앞 인덱스의 문자열로 시작하는지 비교
       for(int i=0;i<phone_book.length-1;i++){
            if(phone_book[i+1].startsWith(phone_book[i]))
                return false;
        }
        return true;
  
    }
}
