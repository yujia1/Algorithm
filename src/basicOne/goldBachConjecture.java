package basicOne;

public class goldBachConjecture {
    static boolean GConjecture(long n){
        if (n % 2 !=0){
            return false;
        }
        // if n can split two prime numbers, return true;
        // otherwise, return false;
        if(isCounterExample(n)){
            return true;
        }else{
            return false;
        }
    }
    static boolean isCounterExample(long n){
        // if counter example that n consist of two prime number, return true;
        // otherwise, return false;
        for (long i = 2; i < n; i++ ){
            if (isPrime(i) && isPrime(n-i)){
                System.out.println("Prime numbers are " + i + " and " + (n-i));
                return true;
            }
        }
        return false;
    }

    static boolean isPrime(long n){
        for (long j = 2; j <= n/2; j++){
            if (n % j == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//       System.out.println(GConjecture(1000000002));
//        System.out.println("73 is prime number: " + isPrime(73));
//       System.out.println("999999929 is prime number: " + isPrime(999999929));
       boolean x = true;
       boolean y = x;
       System.out.println( x == y);
       y = false;
       System.out.println( x == y);

    }

}
