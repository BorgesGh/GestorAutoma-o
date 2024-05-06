package org.automacao.control;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.IteratorUtils.toList;

public class GerenciadorDePlanilha {
    Sheet planilha;

    public GerenciadorDePlanilha() {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/BancoDeDados.xlsx");
            XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(file);
            planilha = workbook.getSheetAt(0);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Row> getLinhas(){
        return toList(planilha.iterator());
    }

    public List<String> getProcessos() {
        List<Row> linhas = getLinhas();

        List<String> processos = new ArrayList<>();

        linhas.forEach(row ->{

            List<Cell> celulas = toList(row.cellIterator());
            processos.add(celulas.getFirst().getStringCellValue());

        });
        return processos;
    }
}
