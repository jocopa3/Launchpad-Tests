package launchpad;

/*
 * Collection of math utilities that's sometimes faster than using the Math
 * class provided by Java.
 *
 * I copy and paste this from project to project, so it's super generic.
 */



/**
 *
 * @author Jocopa3
 */
public class MathUtils {
    //Faster than trying to access Math.pow
    public static int min(int a, int b) {
        return (a <= b) ? a : b;
    }
    
    //Faster than trying to access Math.pow
    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }
    
    //returns the min value in an array
    public static int min(int[] arr){
        int mi = arr[0];
        for(int i : arr)
            mi = i<mi ? i : mi;
        return mi;
    }
    
    //returns the max value in an array
    public static int max(int[] arr){
        int ma = arr[0];
        for(int i : arr)
            ma = i>ma ? i : ma;
        return ma;
    }
    
    //Faster than trying to access Math.pow
    public static float min(float a, float b) {
        return (a <= b) ? a : b;
    }
    
    //Faster than trying to access Math.pow
    public static float max(float a, float b) {
        return (a >= b) ? a : b;
    }
    
    //Faster than trying to access Math.pow
    public static double min(double a, double b) {
        return (a <= b) ? a : b;
    }
    
    //Faster than trying to access Math.pow
    public static double max(double a, double b) {
        return (a >= b) ? a : b;
    }
    
    //Incase I'm to lazy to actually type out x*x
    public static int sqr(int a){
        return a*a;
    }
    
    //Incase I'm to lazy to actually type out x*x
    public static float sqr(float a){
        return a*a;
    }
    
    //Faster than trying to access Math.pow
    public static int pow(int a, int pow){
        int b = a;
        for(int i = 1; i < pow; i++)
            b *= a;
        
        return b;
    }
    
    //Faster than trying to access Math.pow
    public static float pow(float a, int pow){
        float b = a;
        if(pow>0)
            for(int i = 1; i < pow; i++)
                b *= a;
        else
            for(int i = 1; i > pow; i--)
                b /= a;
        
        return b;
    }
    
    //Square root is too costly, so most methods will use distance squared
    public static int distSqrd(int a1, int a2, int b1, int b2){
        return sqr(b1-a1)+sqr(b2-a2);
    }
    
    //Square root is too costly, so most methods will use distance squared
    public static float distSqrd(float a1, float a2, float b1, float b2){
        return sqr(b1-a1)+sqr(b2-a2);
    }
    
    //Returns the ceil of a float; not accurate with negative numbers
    public static int ceil(float a){
        return (int)a+1;
    }
    
    //Returns the floor of a float; not accurate with negative numbers
    public static int floor(float a){
        return (int)a;
    }
    
    //Rounds a float up or down depending on the decimal
    public static int round(float a){
        return (a-(int)a) >= 0.5 ? (int)a+1 : (int)a;
    }
    
    //Rounds a float up or down with specified decimal precision
    public static float round(float a, int precision){
        float p = pow(10, precision);
        return round(a*p)/p;
    }
    
    //A quick version of Math.exp; uses the definition exp where e^x = lim(n->infinity, (1+1/n)^n)
    //Sacrafices accuracy for speed, but I'm already using floats, do you think I'd care about precise accuracy :p
    //Max value for precision before it becomes wrong is 22, but anything higher than 10 won't make any difference
    public static float exp(float x, int precision) {
        int pow = 2 << (precision-1); //2^precision
        x = 1 + x/pow;
        
        for(int i = 0; i < precision; i++)
            x *= x;
        
        return x;
    }
    
    public static float exp(float x){
        return exp(x, 10); //Default precision is 2^10;
    }
    
    //A quick version of Math.exp; uses the definition exp where e^x = lim(n->infinity, (1+1/n)^n)
    //Sacrafices accuracy for speed, but I'm already using floats, do you think I'd care about precise accuracy :p
    //Max value for precision before it becomes wrong is 22, but anything higher than 10 probably won't make any difference
    public static double exp(double x, int precision) {
        int pow = 2 << (precision-1); //2^precision
        x = 1 + x/pow;
        
        for(int i = 0; i < precision; i++)
            x *= x;
        
        return x;
    }
    
    public static double exp(double x){
        return exp(x, 10); //Default precision is 2^10;
    }
}
