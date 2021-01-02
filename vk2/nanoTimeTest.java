package tra2.vk2;// nanoTimeTest.java SJ
// esimerkki nanoTime():n viiveistÃ¤
// ei prosessorit ja eri kÃ¤yttÃ¶jÃ¤rjestelmÃ¤t antavat erilaisia tuloksia
// viive: kauanko nanoTime() kestÃ¤Ã¤
// resoluutio: kuinka usein arvo pÃ¤ivittyy

public class nanoTimeTest {

    public static void main(String [] args) {


        int N = 100; // montako kertaa nanoTime() kutsutaan
        int K = 5; // montako kertaa testiÃ¤ kutsutaan
        int T = 5; // max montako eroa tulostetaan / ajo

        if (args.length > 0)
            N = Integer.valueOf(args[0]);

        if (args.length > 1)
            K = Integer.valueOf(args[1]);

        if (args.length > 2)
            T = Integer.valueOf(args[2]);

        for (int k = 0; k < K; k++) 
            luuppitauluun(N, T);

    } // main()


    static void luuppitauluun(int n, int tul) {

        long[] tulos = new long[n];

        for (int i = 0; i < n; i++)
            tulos[i] = System.nanoTime();

        long[] erot = new long[n-1];

        for (int i = 1; i < n; i++) {
            if (i < tul+1)
                System.out.println(tulos[i] + " " + (tulos[i]-tulos[i-1]));
            erot[i-1] = tulos[i]-tulos[i-1];
        }

        long avg = (tulos[n-1]-tulos[0])/(n-1);

        java.util.Arrays.sort(erot);

        System.out.println("min=" + erot[0] + " avg=" + avg + " med=" + erot[n/2] + " max=" + erot[n-2]);


    }


}