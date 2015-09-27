package com.bitarts.parsian.service.calculations.Constants;

/**
 * Created by IntelliJ IDEA.
 * User: Faraz
 * Date: Feb 7, 2011
 * Time: 11:40:22 PM
 */
public class LifeTable {
    private static double LIFE_TABLE[][] =       //be nerkhe bahrevari vabaste ast va nemitavaan saabet dar nazar gereft

            {
                    //  Age         dx          lx         V^x     V^(x+1)       DX           CX         MX         qx       age      premium
                    {0.000000, 871.000000, 100000.000000, 1.000000, 0.869565, 100000.000000, 757.391304, 1041.489157, 0.008710, 0.000000, 0.007574,},
                    {1.000000, 72.000000, 99129.000000, 0.869565, 0.756144, 86199.130435, 54.442344, 284.097852, 0.000726, 1.000000, 0.000632,},
                    {2.000000, 47.000000, 99057.000000, 0.756144, 0.657516, 74901.323251, 30.903263, 229.655508, 0.000474, 2.000000, 0.000413,},
                    {3.000000, 33.000000, 99010.000000, 0.657516, 0.571753, 65100.682173, 18.867857, 198.752245, 0.000333, 3.000000, 0.000290,},
                    {4.000000, 29.000000, 98977.000000, 0.571753, 0.497177, 56590.420989, 14.418125, 179.884388, 0.000293, 4.000000, 0.000255,},
                    {5.000000, 27.000000, 98948.000000, 0.497177, 0.432328, 49194.643604, 11.672845, 165.466263, 0.000273, 5.000000, 0.000237,},
                    {6.000000, 24.000000, 98921.000000, 0.432328, 0.375937, 42766.278115, 9.022489, 153.793418, 0.000243, 6.000000, 0.000211,},
                    {7.000000, 21.000000, 98897.000000, 0.375937, 0.326902, 37179.045437, 6.864937, 144.770929, 0.000212, 7.000000, 0.000185,},
                    {8.000000, 21.000000, 98876.000000, 0.326902, 0.284262, 32322.739791, 5.969511, 137.905992, 0.000212, 8.000000, 0.000185,},
                    {9.000000, 20.000000, 98855.000000, 0.284262, 0.247185, 28100.760742, 4.943694, 131.936481, 0.000202, 9.000000, 0.000176,},
                    {10.000000, 21.000000, 98835.000000, 0.247185, 0.214943, 24430.500430, 4.513808, 126.992787, 0.000212, 10.000000, 0.000185,},
                    {11.000000, 21.000000, 98814.000000, 0.214943, 0.186907, 21239.399609, 3.925050, 122.478979, 0.000213, 11.000000, 0.000185,},
                    {12.000000, 22.000000, 98793.000000, 0.186907, 0.162528, 18465.118088, 3.575615, 118.553929, 0.000223, 12.000000, 0.000194,},
                    {13.000000, 26.000000, 98771.000000, 0.162528, 0.141329, 16053.048810, 3.674545, 114.978314, 0.000263, 13.000000, 0.000229,},
                    {14.000000, 33.000000, 98745.000000, 0.141329, 0.122894, 13955.498333, 4.055518, 111.303769, 0.000334, 14.000000, 0.000291,},
                    {15.000000, 45.000000, 98712.000000, 0.122894, 0.106865, 12131.160424, 4.808915, 107.248251, 0.000456, 15.000000, 0.000396,},
                    {16.000000, 61.000000, 98667.000000, 0.106865, 0.092926, 10544.026236, 5.668479, 102.439336, 0.000618, 16.000000, 0.000538,},
                    {17.000000, 86.000000, 98606.000000, 0.092926, 0.080805, 9163.049987, 6.949240, 96.770857, 0.000872, 17.000000, 0.000758,},
                    {18.000000, 114.000000, 98520.000000, 0.080805, 0.070265, 7960.920314, 8.010247, 89.821617, 0.001157, 18.000000, 0.001006,},
                    {19.000000, 129.000000, 98406.000000, 0.070265, 0.061100, 6914.529157, 7.881936, 81.811370, 0.001311, 19.000000, 0.001140,},
                    {20.000000, 140.000000, 98277.000000, 0.061100, 0.053131, 6004.752113, 7.438295, 73.929434, 0.001425, 20.000000, 0.001239,},
                    {21.000000, 150.000000, 98137.000000, 0.053131, 0.046201, 5214.085282, 6.930088, 66.491140, 0.001528, 21.000000, 0.001329,},
                    {22.000000, 157.000000, 97987.000000, 0.046201, 0.040174, 4527.057113, 6.307385, 59.561051, 0.001602, 22.000000, 0.001393,},
                    {23.000000, 153.000000, 97830.000000, 0.040174, 0.034934, 3930.264018, 5.344945, 53.253667, 0.001564, 23.000000, 0.001360,},
                    {24.000000, 153.000000, 97677.000000, 0.034934, 0.030378, 3412.275940, 4.647778, 47.908721, 0.001566, 24.000000, 0.001362,},
                    {25.000000, 151.000000, 97524.000000, 0.030378, 0.026415, 2962.548691, 3.988716, 43.260943, 0.001548, 25.000000, 0.001346,},
                    {26.000000, 151.000000, 97373.000000, 0.026415, 0.022970, 2572.140581, 3.468449, 39.272227, 0.001551, 26.000000, 0.001348,},
                    {27.000000, 152.000000, 97222.000000, 0.022970, 0.019974, 2233.175535, 3.036016, 35.803778, 0.001563, 27.000000, 0.001360,},
                    {28.000000, 154.000000, 97070.000000, 0.019974, 0.017369, 1938.855753, 2.674751, 32.767762, 0.001586, 28.000000, 0.001380,},
                    {29.000000, 157.000000, 96916.000000, 0.017369, 0.015103, 1683.286774, 2.371180, 30.093011, 0.001620, 29.000000, 0.001409,},
                    {30.000000, 162.000000, 96759.000000, 0.015103, 0.013133, 1461.356450, 2.127561, 27.721832, 0.001674, 30.000000, 0.001456,},
                    {31.000000, 168.000000, 96597.000000, 0.013133, 0.011420, 1268.617178, 1.918573, 25.594271, 0.001739, 31.000000, 0.001512,},
                    {32.000000, 174.000000, 96429.000000, 0.011420, 0.009931, 1101.226799, 1.727908, 23.675698, 0.001804, 32.000000, 0.001569,},
                    {33.000000, 184.000000, 96255.000000, 0.009931, 0.008635, 955.860613, 1.588881, 21.947790, 0.001912, 33.000000, 0.001662,},
                    {34.000000, 193.000000, 96071.000000, 0.008635, 0.007509, 829.594261, 1.449215, 20.358910, 0.002009, 34.000000, 0.001747,},
                    {35.000000, 202.000000, 95878.000000, 0.007509, 0.006529, 719.937099, 1.318952, 18.909694, 0.002107, 35.000000, 0.001832,},
                    {36.000000, 213.000000, 95676.000000, 0.006529, 0.005678, 624.713308, 1.209371, 17.590742, 0.002226, 36.000000, 0.001936,},
                    {37.000000, 226.000000, 95463.000000, 0.005678, 0.004937, 542.019592, 1.115811, 16.381371, 0.002367, 37.000000, 0.002059,},
                    {38.000000, 240.000000, 95237.000000, 0.004937, 0.004293, 470.205574, 1.030375, 15.265561, 0.002520, 38.000000, 0.002191,},
                    {39.000000, 251.000000, 94997.000000, 0.004293, 0.003733, 407.844037, 0.937044, 14.235185, 0.002642, 39.000000, 0.002298,},
                    {40.000000, 270.000000, 94746.000000, 0.003733, 0.003246, 353.709944, 0.876501, 13.298141, 0.002850, 40.000000, 0.002478,},
                    {41.000000, 294.000000, 94476.000000, 0.003246, 0.002823, 306.697364, 0.829923, 12.421640, 0.003112, 41.000000, 0.002706,},
                    {42.000000, 314.000000, 94182.000000, 0.002823, 0.002455, 265.863436, 0.770766, 11.591717, 0.003334, 42.000000, 0.002899,},
                    {43.000000, 353.000000, 93868.000000, 0.002455, 0.002134, 230.414831, 0.753477, 10.820951, 0.003761, 43.000000, 0.003270,},
                    {44.000000, 382.000000, 93515.000000, 0.002134, 0.001856, 199.607246, 0.709023, 10.067474, 0.004085, 44.000000, 0.003552,},
                    {45.000000, 406.000000, 93133.000000, 0.001856, 0.001614, 172.862495, 0.655278, 9.358451, 0.004359, 45.000000, 0.003791,},
                    {46.000000, 432.000000, 92727.000000, 0.001614, 0.001403, 149.659935, 0.606297, 8.703173, 0.004659, 46.000000, 0.004051,},
                    {47.000000, 462.000000, 92295.000000, 0.001403, 0.001220, 129.532777, 0.563827, 8.096876, 0.005006, 47.000000, 0.004353,},
                    {48.000000, 501.000000, 91833.000000, 0.001220, 0.001061, 112.073371, 0.531672, 7.533050, 0.005456, 48.000000, 0.004744,},
                    {49.000000, 554.000000, 91332.000000, 0.001061, 0.000923, 96.923434, 0.511232, 7.001378, 0.006066, 49.000000, 0.005275,},
                    {50.000000, 607.000000, 90778.000000, 0.000923, 0.000802, 83.770015, 0.487078, 6.490146, 0.006687, 50.000000, 0.005814,},
                    {51.000000, 660.000000, 90171.000000, 0.000802, 0.000698, 72.356413, 0.460528, 6.003068, 0.007319, 51.000000, 0.006365,},
                    {52.000000, 720.000000, 89511.000000, 0.000698, 0.000607, 62.458092, 0.436865, 5.542540, 0.008044, 52.000000, 0.006995,},
                    {53.000000, 780.000000, 88791.000000, 0.000607, 0.000528, 53.874519, 0.411539, 5.105675, 0.008785, 53.000000, 0.007639,},
                    {54.000000, 846.000000, 88011.000000, 0.000528, 0.000459, 46.435869, 0.388141, 4.694136, 0.009612, 54.000000, 0.008359,},
                    {55.000000, 924.000000, 87165.000000, 0.000459, 0.000399, 39.990876, 0.368632, 4.305995, 0.010601, 55.000000, 0.009218,},
                    {56.000000, 985.000000, 86241.000000, 0.000399, 0.000347, 34.406043, 0.341711, 3.937363, 0.011421, 56.000000, 0.009932,},
                    {57.000000, 1045.000000, 85256.000000, 0.000347, 0.000302, 29.576587, 0.315240, 3.595652, 0.012257, 57.000000, 0.010658,},
                    {58.000000, 1128.000000, 84211.000000, 0.000302, 0.000262, 25.403531, 0.295894, 3.280412, 0.013395, 58.000000, 0.011648,},
                    {59.000000, 1199.000000, 83083.000000, 0.000262, 0.000228, 21.794133, 0.273495, 2.984518, 0.014431, 59.000000, 0.012549,},
                    {60.000000, 1282.000000, 81884.000000, 0.000228, 0.000198, 18.677925, 0.254284, 2.711023, 0.015656, 60.000000, 0.013614,},
                    {61.000000, 1359.000000, 80602.000000, 0.000198, 0.000172, 15.987390, 0.234398, 2.456739, 0.016861, 61.000000, 0.014661,},
                    {62.000000, 1436.000000, 79243.000000, 0.000172, 0.000150, 13.667680, 0.215373, 2.222341, 0.018121, 62.000000, 0.015758,},
                    {63.000000, 1512.000000, 77807.000000, 0.000150, 0.000130, 11.669567, 0.197192, 2.006968, 0.019433, 63.000000, 0.016898,},
                    {64.000000, 1575.000000, 76295.000000, 0.000130, 0.000113, 9.950257, 0.178616, 1.809776, 0.020644, 64.000000, 0.017951,},
                    {65.000000, 1645.000000, 74720.000000, 0.000113, 0.000099, 8.473781, 0.162222, 1.631160, 0.022016, 65.000000, 0.019144,},
                    {66.000000, 1709.000000, 73075.000000, 0.000099, 0.000086, 7.206284, 0.146550, 1.468938, 0.023387, 66.000000, 0.020336,},
                    {67.000000, 1807.000000, 71366.000000, 0.000086, 0.000075, 6.119783, 0.134743, 1.322388, 0.025320, 67.000000, 0.022018,},
                    {68.000000, 1904.000000, 69559.000000, 0.000075, 0.000065, 5.186808, 0.123457, 1.187645, 0.027372, 68.000000, 0.023802,},
                    {69.000000, 2006.000000, 67655.000000, 0.000065, 0.000056, 4.386811, 0.113105, 1.064188, 0.029650, 69.000000, 0.025783,},
                    {70.000000, 2106.000000, 65649.000000, 0.000056, 0.000049, 3.701513, 0.103255, 0.951083, 0.032080, 70.000000, 0.027895,},
                    {71.000000, 2258.000000, 63543.000000, 0.000049, 0.000043, 3.115452, 0.096267, 0.847828, 0.035535, 71.000000, 0.030900,},
                    {72.000000, 2374.000000, 61285.000000, 0.000043, 0.000037, 2.612821, 0.088011, 0.751560, 0.038737, 72.000000, 0.033684,},
                    {73.000000, 2495.000000, 58911.000000, 0.000037, 0.000032, 2.184007, 0.080432, 0.663549, 0.042352, 73.000000, 0.036828,},
                    {74.000000, 2598.000000, 56416.000000, 0.000032, 0.000028, 1.818704, 0.072828, 0.583117, 0.046051, 74.000000, 0.040044,},
                    {75.000000, 2732.000000, 53818.000000, 0.000028, 0.000024, 1.508654, 0.066595, 0.510288, 0.050764, 75.000000, 0.044142,},
                    {76.000000, 2835.000000, 51086.000000, 0.000024, 0.000021, 1.245277, 0.060092, 0.443693, 0.055495, 76.000000, 0.048256,},
                    {77.000000, 2967.000000, 48251.000000, 0.000021, 0.000018, 1.022757, 0.054687, 0.383601, 0.061491, 77.000000, 0.053470,},
                    {78.000000, 3081.000000, 45284.000000, 0.000018, 0.000016, 0.834667, 0.049381, 0.328913, 0.068037, 78.000000, 0.059163,},
                    {79.000000, 3162.000000, 42203.000000, 0.000016, 0.000014, 0.676416, 0.044069, 0.279532, 0.074924, 79.000000, 0.065151,},
                    {80.000000, 3217.000000, 39041.000000, 0.000014, 0.000012, 0.544119, 0.038988, 0.235463, 0.082401, 80.000000, 0.071653,},
                    {81.000000, 3306.000000, 35824.000000, 0.000012, 0.000011, 0.434159, 0.034840, 0.196475, 0.092285, 81.000000, 0.080247,},
                    {82.000000, 3298.000000, 32518.000000, 0.000011, 0.000009, 0.342690, 0.030222, 0.161635, 0.101421, 82.000000, 0.088192,},
                    {83.000000, 3258.000000, 29220.000000, 0.000009, 0.000008, 0.267768, 0.025962, 0.131413, 0.111499, 83.000000, 0.096956,},
                    {84.000000, 3182.000000, 25962.000000, 0.000008, 0.000007, 0.206880, 0.022049, 0.105451, 0.122564, 84.000000, 0.106577,},
                    {85.000000, 3055.000000, 22780.000000, 0.000007, 0.000006, 0.157847, 0.018408, 0.083402, 0.134109, 85.000000, 0.116616,},
                    {86.000000, 2882.000000, 19725.000000, 0.000006, 0.000005, 0.118851, 0.015100, 0.064995, 0.146109, 86.000000, 0.127051,},
                    {87.000000, 2710.000000, 16843.000000, 0.000005, 0.000005, 0.088249, 0.012347, 0.049895, 0.160898, 87.000000, 0.139911,},
                    {88.000000, 2508.000000, 14133.000000, 0.000005, 0.000004, 0.064391, 0.009936, 0.037548, 0.177457, 88.000000, 0.154310,},
                    {89.000000, 2236.000000, 11625.000000, 0.000004, 0.000003, 0.046056, 0.007703, 0.027611, 0.192344, 89.000000, 0.167256,},
                    {90.000000, 1951.000000, 9389.000000, 0.000003, 0.000003, 0.032345, 0.005845, 0.019908, 0.207796, 90.000000, 0.180692,},
                    {91.000000, 1675.000000, 7438.000000, 0.000003, 0.000003, 0.022282, 0.004363, 0.014064, 0.225195, 91.000000, 0.195822,},
                    {92.000000, 1413.000000, 5763.000000, 0.000003, 0.000002, 0.015012, 0.003201, 0.009700, 0.245185, 92.000000, 0.213204,},
                    {93.000000, 1139.000000, 4350.000000, 0.000002, 0.000002, 0.009853, 0.002244, 0.006500, 0.261839, 93.000000, 0.227686,},
                    {94.000000, 896.000000, 3211.000000, 0.000002, 0.000002, 0.006325, 0.001535, 0.004256, 0.279041, 94.000000, 0.242644,},
                    {95.000000, 680.000000, 2315.000000, 0.000002, 0.000001, 0.003965, 0.001013, 0.002722, 0.293737, 95.000000, 0.255423,},
                    {96.000000, 520.000000, 1635.000000, 0.000001, 0.000001, 0.002435, 0.000673, 0.001709, 0.318043, 96.000000, 0.276559,},
                    {97.000000, 375.000000, 1115.000000, 0.000001, 0.000001, 0.001444, 0.000422, 0.001035, 0.336323, 97.000000, 0.292455,},
                    {98.000000, 287.000000, 740.000000, 0.000001, 0.000001, 0.000833, 0.000281, 0.000613, 0.387838, 98.000000, 0.337250,},
                    {99.000000, 190.000000, 453.000000, 0.000001, 0.000001, 0.000444, 0.000162, 0.000332, 0.419426, 99.000000, 0.364718,},
                    {100.000000, 118.000000, 263.000000, 0.000001, 0.000001, 0.000224, 0.000087, 0.000170, 0.448669, 100.000000, 0.390147,},
                    {101.000000, 69.000000, 145.000000, 0.000001, 0.000001, 0.000107, 0.000044, 0.000083, 0.475862, 101.000000, 0.413793,},
                    {102.000000, 39.000000, 76.000000, 0.000001, 0.000001, 0.000049, 0.000022, 0.000038, 0.513158, 102.000000, 0.446224,},
                    {103.000000, 20.000000, 37.000000, 0.000001, 0.000000, 0.000021, 0.000010, 0.000017, 0.540541, 103.000000, 0.470035,},
                    {104.000000, 10.000000, 17.000000, 0.000000, 0.000000, 0.000008, 0.000004, 0.000007, 0.588235, 104.000000, 0.511509,},
                    {105.000000, 7.000000, 7.000000, 0.000000, 0.000000, 0.000003, 0.000003, 0.000003, 1.000000, 105.000000, 0.869565,}};
    //  Age         dx         lx        V^x      V^(x+1)     DX         CX        MX       qx        age      premium

    public static double[][] getLifeTable(double nerkheBahreFanni) {
        for (int i = 0; i < 106; i++) {
            // V^x
            LIFE_TABLE[i][3] = 1.0 / Math.pow(1.0 + nerkheBahreFanni, LIFE_TABLE[i][0]);
            // V^(x+1)
            LIFE_TABLE[i][4] = LIFE_TABLE[i][3] / (1.0 + nerkheBahreFanni);
            // Dx
            LIFE_TABLE[i][5] = LIFE_TABLE[i][2] * LIFE_TABLE[i][3];
            // Cx
            LIFE_TABLE[i][6] = LIFE_TABLE[i][1] * LIFE_TABLE[i][4];
            // qx
            LIFE_TABLE[i][8] = LIFE_TABLE[i][1] / LIFE_TABLE[i][2];
            // premium
            LIFE_TABLE[i][10] = LIFE_TABLE[i][6] / LIFE_TABLE[i][2];
        }
        // Mx
        for (int i = 0; i < 106; i++) {
            LIFE_TABLE[i][7] = 0.0;
            for (int j = i; j < 106; j++)
                LIFE_TABLE[i][7] += LIFE_TABLE[j][6];
        }
        return LIFE_TABLE;
    }
}