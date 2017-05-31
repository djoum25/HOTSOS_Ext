package com.teamtreehouselearningjulienlaurent.hotsos_ext.utility;

/**
 * Created by djoum on 5/30/17.
 */

public class RoomsUtility {
    /**
     * @param room     to check the value of the room depend on
     * @param building that user chooses and
     * @return the right room number to send to the other fragment
     * @throws Exception in case room do not match
     */
    public static String roomForOrder(String building, String room) throws Exception {
        //Toast.makeText(getActivity(), room.substring(1, 4), Toast.LENGTH_SHORT).show();
        int roomNumberInteger = Integer.parseInt(room);
        StringBuilder correctRoom = new StringBuilder();

        if ((building.equalsIgnoreCase("tresor")) &&
            ((roomNumberInteger >= 701 && roomNumberInteger <= 717 &&
                roomNumberInteger != 706 && roomNumberInteger != 708 &&
                roomNumberInteger != 710 && roomNumberInteger != 713 && roomNumberInteger != 713) ||
                (roomNumberInteger >= 801 && roomNumberInteger < 817 && roomNumberInteger != 813) ||
                (roomNumberInteger >= 901 && roomNumberInteger < 917 && roomNumberInteger != 913))) {
            correctRoom.append("20-").append(room);
        } else if ((building.equalsIgnoreCase("tresor") &&
            ((roomNumberInteger >= 1001 && roomNumberInteger <= 1017 && roomNumberInteger != 1013) ||
                (roomNumberInteger >= 1101 && roomNumberInteger <= 1117 && roomNumberInteger != 1113) ||
                (roomNumberInteger >= 1201 && roomNumberInteger <= 1217 && roomNumberInteger != 1213) ||
                (roomNumberInteger >= 1401 && roomNumberInteger <= 1417 && roomNumberInteger != 1413) ||
                (roomNumberInteger >= 1501 && roomNumberInteger <= 1517 && roomNumberInteger != 1513) ||
                (roomNumberInteger >= 1601 && roomNumberInteger <= 1617 && roomNumberInteger != 1613) ||
                (roomNumberInteger >= 1701 && roomNumberInteger <= 1717 && roomNumberInteger != 1713) ||
                (roomNumberInteger >= 1801 && roomNumberInteger <= 1817 && roomNumberInteger != 1813) ||
                (roomNumberInteger >= 1901 && roomNumberInteger <= 1917 && roomNumberInteger != 1913) ||
                (roomNumberInteger >= 2001 && roomNumberInteger <= 2017 && roomNumberInteger != 2013) ||
                (roomNumberInteger >= 2101 && roomNumberInteger <= 2117 && roomNumberInteger != 2113) ||
                (roomNumberInteger >= 2201 && roomNumberInteger <= 2217 && roomNumberInteger != 2213) ||
                (roomNumberInteger >= 2301 && roomNumberInteger <= 2317 && roomNumberInteger != 2313) ||
                (roomNumberInteger >= 2401 && roomNumberInteger <= 2417 && roomNumberInteger != 2413) ||
                (roomNumberInteger >= 2501 && roomNumberInteger <= 2517 && roomNumberInteger != 2513) ||
                (roomNumberInteger >= 2601 && roomNumberInteger <= 2617 && roomNumberInteger != 2613) ||
                (roomNumberInteger >= 2701 && roomNumberInteger <= 2717 && roomNumberInteger != 2713) ||
                (roomNumberInteger >= 2801 && roomNumberInteger <= 2817 && roomNumberInteger != 2813) ||
                (roomNumberInteger >= 2901 && roomNumberInteger <= 2917 && roomNumberInteger != 2913) ||
                (roomNumberInteger >= 3001 && roomNumberInteger <= 3017 && roomNumberInteger != 3013) ||
                (roomNumberInteger >= 3101 && roomNumberInteger <= 3117 && roomNumberInteger != 3113) ||
                (roomNumberInteger >= 3201 && roomNumberInteger <= 3217 && roomNumberInteger != 3213) ||
                (roomNumberInteger >= 3301 && roomNumberInteger <= 3317 && roomNumberInteger != 3313) ||
                (roomNumberInteger >= 3401 && roomNumberInteger <= 3417 && roomNumberInteger != 3413) ||
                (roomNumberInteger >= 3501 && roomNumberInteger <= 3517 && roomNumberInteger != 3513) ||
                (roomNumberInteger >= 3601 && roomNumberInteger <= 3617 && roomNumberInteger != 3613) ||
                (roomNumberInteger >= 3701 && roomNumberInteger <= 3702)))) {
            correctRoom.append("2-").append(room);
        } else if (building.equalsIgnoreCase("Sorento") && roomNumberInteger >= 301 && roomNumberInteger < 1001) {
            correctRoom.append("30-").append(room);
        } else if (building.equalsIgnoreCase("Sorento") && roomNumberInteger >= 1001 && roomNumberInteger <= 1919) {
            correctRoom.append("3-").append(room);
        } else if ((((building.equalsIgnoreCase("chateau") || (building.equalsIgnoreCase("versailles"))) &&
            roomNumberInteger >= 400 && roomNumberInteger <= 1990))) {
            correctRoom.append("").append(room);
        } else {
            throw new Exception("No such room in the " + building + " building");
        }
        return correctRoom.toString();
    }


}
