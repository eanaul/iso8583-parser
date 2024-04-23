package com.parsing.isosa;

import java.io.IOException;
import java.io.InputStream;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

public class Unpack {

    public static void main(String[] args) {
        Unpack unpacker = new Unpack();
        try {
            ISOMsg isoMsg = unpacker.parseISOMessage();
            unpacker.printISOMessage(isoMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ISOMsg parseISOMessage() throws Exception {
        String isoMessage = "0200F23A400188E0800400000000060000001962714200000000001372110000003750000001209095734031679175734120912106011031280312800000000266300680003000000000680003KC PEJANGGIK CRM         MATARAM NTB ID 3600960210G0010000001        0005000375000000CASH IN   20S0060203373271     0005000375000000CASH IN   130060203373271130060203373271";
        try (InputStream is = getClass().getResourceAsStream("/field.xml")) {
            GenericPackager packager = new GenericPackager(is);
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);
            isoMsg.unpack(isoMessage.getBytes());
            return isoMsg;
        } catch (ISOException | IOException e) {
            throw new Exception("Error parsing ISO message", e);
        }
    }

    private void printISOMessage(ISOMsg isoMsg) {
        try {
            System.out.println("MTI='" + isoMsg.getMTI() + "'");
            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i))
                    System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
            }
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }
}
