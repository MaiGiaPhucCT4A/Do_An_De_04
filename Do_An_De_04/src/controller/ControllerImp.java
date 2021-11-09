/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DatHang;
import model.SanPham;

/**
 *
 * @author LaptopDT
 */
public class ControllerImp {

    private static final String currentDir = System.getProperty("user.dir");
    private static final String separator = File.separator;
    private static final String PATH_FILE_CSV_SanPham = currentDir + separator + "data" + separator + "FileCSV_SanPham_01.csv";

    public void WriteFileJson_SanPham(List<SanPham> list, String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<SanPham> ReadFileJson_SanPham(String filename) {
        FileReader fr = null;
        List<SanPham> listsp = new ArrayList<>();
        try {
            fr = new FileReader(filename);
            Gson gson = new Gson();
            Type classOfT = new TypeToken<List<SanPham>>() {
            }.getType();
            listsp = gson.fromJson(filename, classOfT);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControllerImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(ControllerImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listsp;
    }

    public void WriteFileCSV_SanPham(List<SanPham> list, String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static List<SanPham> list = null;

//    public List<SanPham> ReadFileCSV_SanPham() {
//        FileReader fr = null;
//        LocalDate HSD;
//        LocalDate NSX;
//        List<SanPham> list = new ArrayList<>();
//        try {
//            fr = new FileReader(PATH_FILE_CSV_SanPham);
//            CSVReader csvReader = new CSVReader(fr);
//            String[] line;
//            line = csvReader.readNext();
//            while ((line = csvReader.readNext()) != null) {
//                NSX = LocalDate.parse(line[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//                HSD = LocalDate.parse(line[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//                int sl = Integer.parseInt(line[5]);
//                float price = Float.parseFloat(line[6]);
//                int id = Integer.parseInt(line[0]);
//                SanPham sp = new SanPham(line[0], line[1], NSX, HSD, line[4], sl, price);
//                list.add(sp);
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ControllerImp.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ControllerImp.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (CsvValidationException ex) {
//            Logger.getLogger(ControllerImp.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                fr.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ControllerImp.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return list;
//
////        FileReader fr = null;
////        try {
////            fr = new FileReader(filename);
////
////            CsvToBean<SanPham> csvToBean = new CsvToBeanBuilder<SanPham>(fr)
////                    .withType(SanPham.class).withSkipLines(1).withIgnoreLeadingWhiteSpace(true).build();
////            list = csvToBean.parse();
////
////        } catch (FileNotFoundException ex) {
////            java.util.logging.Logger.getLogger(ControllerImp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
////        } finally {
////            try {
////                fr.close();
////            } catch (IOException ex) {
////                java.util.logging.Logger.getLogger(ControllerImp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
////            }
////        }
////        return list;
//    }
    public void sortByName(List<SanPham> listSP) {
        listSP.sort(new Comparator<SanPham>() {
            @Override
            public int compare(SanPham o1, SanPham o2) { //o1>o2 xếp theo A-Z, 1-10
                return o1.getTenSP().compareTo(o2.getTenSP());
            }
        });
    }

    public void sortByMaSP(List<SanPham> listSP) {
        listSP.sort(new Comparator<SanPham>() {
            @Override
            public int compare(SanPham o1, SanPham o2) { //o1>o2 xếp theo A-Z, 1-10
                return o1.getMaSP().compareTo(o2.getMaSP());
            }
        });
    }

    public void sortByquantity_ASC(List<SanPham> listSP) {
        listSP.sort(new Comparator<SanPham>() {
            @Override
            public int compare(SanPham o1, SanPham o2) {
                if (o1.getSl() > o2.getSl()) {
                    return 1;
                } else if (o1.getSl() < o2.getSl()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    public void sortByquantity_DEC(List<SanPham> listSP) {
        listSP.sort(new Comparator<SanPham>() {
            @Override
            public int compare(SanPham o1, SanPham o2) {
                if (o1.getSl() > o2.getSl()) {
                    return -1;
                } else if (o1.getSl() < o2.getSl()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    public void sortByMoney_ASC(List<SanPham> listSP) {
        listSP.sort(new Comparator<SanPham>() {
            @Override
            public int compare(SanPham o1, SanPham o2) {
                if (o1.getGiaBan() > o2.getGiaBan()) {
                    return 1;
                } else if (o1.getGiaBan() < o2.getGiaBan()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    public void sortByMoney_DEC(List<SanPham> listSP) {
        listSP.sort(new Comparator<SanPham>() {
            @Override
            public int compare(SanPham o1, SanPham o2) {
                if (o1.getGiaBan() < o2.getGiaBan()) {
                    return 1;
                } else if (o1.getGiaBan() > o2.getGiaBan()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    public void sortByMaDH(List<DatHang> listDH) {
        listDH.sort(new Comparator<DatHang>() {
            @Override
            public int compare(DatHang o1, DatHang o2) {
                return o1.getMaHD().compareTo(o2.getMaHD());
            }
        });
    }

}
