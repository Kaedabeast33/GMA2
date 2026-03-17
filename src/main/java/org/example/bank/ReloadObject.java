package org.example.bank;

import com.opencsv.CSVReader;
import org.example.bank.OutputClassBank.EntityInterface;


import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class ReloadObject {
    String identifier;
    CSVReader csvReader;
    String directoryPath;
    LocalDate[] dateRange; // start and end date in "yyyy-MM-dd" format
    Reader reader;
    Iterator<EntityInterface> iterator;

//    Supplier<T> reloadSupplier;

    List<EntityInterface> entities;

//    public Supplier<T> getReloadSupplier() {
//        return reloadSupplier;
//    }
//
//    public void setReloadSupplier(Supplier<T> reloadSupplier) {
//        this.reloadSupplier = reloadSupplier;
//    }

    public Iterator<EntityInterface> getIterator() {
        return iterator;
    }

    public void setIterator(Iterator<EntityInterface> iterator) {
        this.iterator = iterator;
    }

    public List<EntityInterface> getEntities() {
        return entities;
    }

    public void setEntities(List<EntityInterface> entities) {
        this.entities = entities;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public CSVReader getCsvReader() {
        return csvReader;
    }

    public void setCsvReader(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public LocalDate[] getDateRange() {
        return dateRange;
    }

    public void setDateRange(LocalDate[] dateRange) {
        this.dateRange = dateRange;
    }

    public static List<EntityInterface> chunkSeq(int chunkSize, Iterator<EntityInterface> iterator){



        List<EntityInterface> chunk = new ArrayList<>(chunkSize);

        System.out.println("Processing data in chunks...");
        while (iterator.hasNext()) {
            System.out.println("------------------------------------");
            EntityInterface entity;
            try {
                entity = iterator.next();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            chunk.add(entity);
            System.out.println("chunk size: " + chunk.size());

            if (chunk.size() >= chunkSize) {
                return chunk;


            }
        }

        if (!chunk.isEmpty()) {
            return chunk;

        }
        return null;
    }
}
