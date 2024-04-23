package com.parsing.isosa;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

public class Pack {

    public static void main(String[] args) {
        try {
            GenericPackager packager = new GenericPackager("resource/field.xml");
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);
            isoMsg.set(0, "0100");
            isoMsg.set(3, "020000");
            isoMsg.set(4, "5000");
            isoMsg.set(7, new SimpleDateFormat("MMddHHmmss").format(new Date()));
            isoMsg.set(11, "123456");
            isoMsg.set(48, "Example Value");
            byte[] bIsoMsg = isoMsg.pack();
            String isoMessage = new String(bIsoMsg, "UTF-8");
            System.out.println("Packed ISO8385 Message = '" + isoMessage + "'");
        } catch (ISOException | IOException e) {
            e.printStackTrace();
        }
    }
}
