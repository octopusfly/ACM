package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1436.
 * 
 * @version 2016-11-21
 * @author Zhang Yufei
 */
public class HihoCoder1436 {
    /**
     * Code list used for encoding and decoding.
     */
    private static final char[] CODE = "0123456789bcdefghjkmnpqrstuvwxyz"
            .toCharArray();

    /**
     * The coordinates.
     */
    private static class Coordinate {
        double x;
        double y;
    }

    /**
     * The number of coordinates to encode.
     */
    private static int n;

    /**
     * The number of geohash codes to decode.
     */
    private static int m;

    /**
     * The coordinates list to encode.
     */
    private static Coordinate[] coors;

    /**
     * The geohash code list to decode.
     */
    private static String[] codes;

    /**
     * The main program.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();

        coors = new Coordinate[n];
        codes = new String[m];

        for (int i = 0; i < n; i++) {
            coors[i] = new Coordinate();
            coors[i].x = scan.nextDouble();
            coors[i].y = scan.nextDouble();
        }

        for (int i = 0; i < m; i++) {
            codes[i] = scan.next();
        }

        for (int i = 0; i < n; i++) {
            encode(coors[i]);
        }
        for (int i = 0; i < m; i++) {
            decode(codes[i]);
        }
        scan.close();
    }

    /**
     * The function used to convert the coordinates into geohash codes.
     * 
     * @param coor
     *            The coordinate to encode.
     */
    private static void encode(Coordinate coor) {
        double latLeft = -90.0;
        double latRight = 90.0;
        double latMid = 0.0;

        double lonLeft = -180.0;
        double lonRight = 180.0;
        double lonMid = 0.0;

        int index = 0;
        int indexCnt = 0;

        for (int i = 0; i < 25; i++) {
            index *= 2;
            if (coor.y - lonMid > 1e-9) {
                lonLeft = lonMid;
                index += 1;
            } else {
                lonRight = lonMid;
            }
            lonMid = (lonLeft + lonRight) / 2;
            indexCnt++;
            if (indexCnt == 5) {
                System.out.print(CODE[index]);
                index = 0;
                indexCnt = 0;
            }

            index *= 2;
            if (coor.x - latMid > 1e-9) {
                latLeft = latMid;
                index += 1;
            } else {
                latRight = latMid;
            }
            latMid = (latLeft + latRight) / 2;
            indexCnt++;
            if (indexCnt == 5) {
                System.out.print(CODE[index]);
                index = 0;
                indexCnt = 0;
            }

        }

        System.out.println();
    }

    /**
     * The function used to convert the geohash codes in the list
     * <code> codes </code> into coordinates.
     * 
     * @param code
     *            The code to decode.
     */
    private static void decode(String code) {
        double latLeft = -90.0;
        double latRight = 90.0;
        double latMid = 0.0;

        double lonLeft = -180.0;
        double lonRight = 180.0;
        double lonMid = 0.0;

        char[] geoCodes = code.toCharArray();
        int[] indexes = new int[geoCodes.length
                * 5];

        for (int i = 0; i < geoCodes.length; i++) {
            int index = -1;
            for (int j = 0; j < 32; j++) {
                if (CODE[j] == geoCodes[i]) {
                    index = j;
                    break;
                }
            }

            indexes[i * 5 + 0] = index / 16;
            indexes[i * 5 + 1] = index % 16 / 8;
            indexes[i * 5 + 2] = index % 8 / 4;
            indexes[i * 5 + 3] = index % 4 / 2;
            indexes[i * 5 + 4] = index % 2;
        }

        for (int i = 0; i < indexes.length; i += 2) {
            if (indexes[i] == 0) {
                lonRight = lonMid;
            } else {
                lonLeft = lonMid;
            }
            lonMid = (lonLeft + lonRight) / 2;
            
            if (indexes[i + 1] == 0) {
                latRight = latMid;
            } else {
                latLeft = latMid;
            }
            latMid = (latLeft + latRight) / 2;
        }

        System.out.printf("%.6f %.6f\n", latMid,
                lonMid);
    }

}