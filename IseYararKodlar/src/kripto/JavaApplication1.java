/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kripto;

import java.util.Random;
/**
 *
 * @author Asus
 */
public class JavaApplication1 {
int giden[]=new int[5];
int gelen[]=new int[5];
int sonuc[]=new int[5];
public int[] A(){
 Random aa=new Random();
 for(int i=0;i<5;i++){
     giden[i]=aa.nextInt(4);  
     System.out.print(giden[i]+ "  ");
 }
  return giden; 
}

public int[] B(int[] abc){
    Random aa=new Random();
    for(int i=0;i<5;i++){
        int b=aa.nextInt(9-7)+7;  
        if(((b==8) && (abc[i]==0))||((b==8)&& (abc[i]==1))){
            gelen[i]=8;
        
        }
        else if(((b==7) && (abc[i]==2))||((b==7)&& (abc[i]==3))){
            gelen[i]=7;
        }
        else{
            gelen[i]=aa.nextInt(9-7)+7;
        }
        System.out.print(gelen[i]+"  ");
    }
    return gelen;
}

public void C(int[] def,int[]ghi){
    for(int i=0;i<5;i++){
       if(((def[i]==8) && (ghi[i]==0))||((def[i]==8)&& (ghi[i]==1))||(((def[i]==7)&&(ghi[i]==2))||((def[i]==7)&&(ghi[i]==3)))) {
           sonuc[i]=1;
       }
       else{
           sonuc[i]=0;
       }
        
        System.out.print(sonuc[i]+ "  ");
    }
    
}

    public static void main(String[] args) {
        JavaApplication1 a=new JavaApplication1();
        int[] c=a.A();
        System.out.println("--------------");
        int[] d=a.B(c);
        System.out.println("------------");
        a.C(d,c);
    }
}
