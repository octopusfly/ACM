package hihocoder;

import java.util.Scanner;
import static java.lang.Math.*;

/**
 * This is the ACM problem solving program for hihoCoder ???.
 * 
 * @version 2016-11-28
 * @author Zhang Yufei
 */
public class HihoCoder1437 {
    /**
     * Code list used for encoding and decoding.
     */
    private static final char[] CODE = "0123456789bcdefghjkmnpqrstuvwxyz".toCharArray();

    /**
     * The length of geohash. (bits)
     */
    private static final int GEO_LEN = 6;

    /**
     * The distance of each area in latitude
     */
    private static final double DELTA_LAT = 180 / pow(2, GEO_LEN * 5 / 2);

    /**
     * The distance of each area in longitude;
     */
    private static final double DELTA_LON = 360 / pow(2, GEO_LEN * 5 / 2);

    /**
     * The radius of earth.
     */
    private static final long RADIUS = 6000000;

    /**
     * Used to convert the angle to rad.
     */
    private static final double ANG_TO_RAD = PI / 180.0;

    /**
     * The number of coordinates.
     */
    private static int n;

    /**
     * The number of center coordinates to compute.
     */
    private static int m;

    /**
     * The coordinates.
     */
    private static class Coordinate {
        /**
         * The coordinate value.
         */
        double x, y;
        /**
         * Used in link list;
         */
        Coordinate next = null;
    }

    /**
     * The node of the trie-tree.
     */
    private static class Node {
        /**
         * Children list.
         */
        Node[] childs = null;

        /**
         * The coordinate list whose geohash is equal. Not-null only in leaf
         * nodes in trie-tree.
         */
        Coordinate coordinateList = null;
    }

    /**
     * The coordinates list.
     */
    private static Coordinate[] coors;

    /**
     * The root node of trie-tree.
     */
    private static Node root;

    /**
     * Used to record geohash.
     */
    private static char[] geohash;

    /**
     * The main program.
     * 
     * @param args
     *            The command-line parameter list.
     */
    public static void main(String[] args) {
        // Input Data.
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();

        coors = new Coordinate[n];

        for (int i = 0; i < n; i++) {
            coors[i] = new Coordinate();
            coors[i].x = scan.nextDouble();
            coors[i].y = scan.nextDouble();
            coors[i].next = null;
        }

        // Build the trie-tree.
        root = new Node();
        root.childs = new Node[CODE.length];
        geohash = new char[GEO_LEN];

        for (int i = 0; i < n; i++) {
            encode(coors[i]);
            // System.out.println(geohash);
            addTree(i);
        }

        // Start Search.
        Coordinate s = new Coordinate();
        Coordinate c = new Coordinate();
        for (int i = 0; i < m; i++) {
            boolean tag = false;
            s.x = scan.nextDouble();
            s.y = scan.nextDouble();

            c.x = s.x - DELTA_LAT;

            if (c.x + 90 < -1e-9) {
                c.x = -180 - c.x;
                tag = true;
            }

            int count = 0;
            for (int latCnt = 0; latCnt < 3; latCnt++) {
                if (tag) {
                    if (s.y > 1e-9) {
                        c.y = s.y - 180 - DELTA_LON;
                    } else {
                        c.y = s.y + 180 - DELTA_LON;
                    }
                    tag = false;
                } else {
                    c.y = s.y - DELTA_LON;
                }

                if (c.y + 180 < -1e-9) {
                    c.y += 360;
                }

                for (int lonCnt = 0; lonCnt < 3; lonCnt++) {
                    encode(c);
                    // System.out.println(geohash);
                    count += search(s);

                    c.y += DELTA_LON;
                    if (c.y - 180 > 1e-9) {
                        c.y -= 360;
                    }
                }
                c.x += DELTA_LAT;
                if (c.x - 90 > 1e-9) {
                    c.x = 180 - c.x;
                    tag = true;
                }
            }

            System.out.println(count);
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

        int geoIndex = 0;

        for (int i = 0; i < GEO_LEN * 5 / 2; i++) {
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
                geohash[geoIndex] = CODE[index];
                index = 0;
                indexCnt = 0;
                geoIndex++;
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
                geohash[geoIndex] = CODE[index];
                index = 0;
                indexCnt = 0;
                geoIndex++;
            }
        }
    }

    /**
     * This function adds a new geohash code into the trie-tree.
     * 
     * @param index
     *            The current index of coordinate.
     */
    private static void addTree(int index) {
        Node p = root;
        for (int i = 0; i < GEO_LEN; i++) {
            int childIndex = getIndex(geohash[i]);

            if (p.childs == null) {
                p.childs = new Node[CODE.length];
            }

            if (p.childs[childIndex] == null) {
                p.childs[childIndex] = new Node();
            }

            p = p.childs[childIndex];
        }

        coors[index].next = p.coordinateList;
        p.coordinateList = coors[index];
    }

    /**
     * Get the index in <code> CODE </code> for given character.
     * 
     * @param ch
     *            The character to search.
     * @return The index in <code> CODE </code>
     */
    private static int getIndex(char ch) {
        if (ch < 'a') {
            return ch - '0';
        } else if (ch < 'i') {
            return ch - 'b' + 10;
        } else if (ch < 'l') {
            return ch - 'j' + 17;
        } else if (ch < 'o') {
            return ch - 'm' + 19;
        } else {
            return ch - 'p' + 21;
        }
    }

    /**
     * Compute the number of nodes whose distance is lower than given
     * coordinate.
     * 
     * @param center
     *            The center coordinate.
     * @return The number of nodes whose distance is lower than given coordinate
     */
    private static int search(Coordinate center) {
        Node p = root;
        for (int i = 0; i < GEO_LEN; i++) {
            int childIndex = getIndex(geohash[i]);
            if (p.childs == null || p.childs[childIndex] == null) {
                return 0;
            }
            p = p.childs[childIndex];
        }

        Coordinate q = p.coordinateList;
        int count = 0;
        while (q != null) {
            double distance = computeDistance(center, q);
            // System.out.println(distance);
            if (distance - 500.0 < 1e-9) {
                count++;
            }

            q = q.next;
        }

        return count;
    }

    /**
     * Compute the distance of two coordinates using math method.
     * 
     * @param x
     *            One coordinate.
     * @param y
     *            Another coordinate.
     * @return The distance of this two coordinates.
     */
    private static double computeDistance(Coordinate x, Coordinate y) {
        return RADIUS * acos(cos((x.y - y.y) * ANG_TO_RAD) * cos((x.x - y.x) * ANG_TO_RAD));
    }
}