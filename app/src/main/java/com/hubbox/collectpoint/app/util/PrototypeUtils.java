package com.hubbox.collectpoint.app.util;

import com.hubbox.collectpoint.app.dto.Parcel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by VMakarenko on 01.05.2016.
 */
public class PrototypeUtils {
    public static List<Parcel> getSampleParcelList(){
        List<Parcel> resultList = new ArrayList<>();
        Collections.addAll(resultList,
                new Parcel().name("John Smith").hubboxCode(4781253L),
                new Parcel().name("Mad Hatter").hubboxCode(4234123L),
                new Parcel().name("Ronald McDonald").hubboxCode(2153725L));
        return resultList;
    }
}
