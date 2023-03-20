/*
Копирует файлы из указанной папки в указанную папку в произвольном порядке
Для записи на SD-карту дл ярандомного воспроизведения в маленькой колонке
Принцип работы воспроизведения файлов в колонке - последний записали, первый воспроизвели
*/
package randomcopyfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RandomCopyFiles {

    public static void main(String[] args) throws IOException {
        System.out.println("Копирует файлы из указанной папки в указанную папку в произвольном порядке");
        System.out.println("Для записи на SD-карту дл ярандомного воспроизведения в маленькой колонке");
        System.out.println("Принцип работы воспроизведения файлов в колонке - последний записали, первый воспроизвели");
        
        System.out.println("--------------------------------");
        System.out.print("Введите путь к источнику файлов: ");
        Scanner input = new Scanner(System.in, "CP1251");
        String pathSourceString = input.nextLine();
        
        //проверяем, что директория существует
        Path pathSource = Paths.get(pathSourceString);
       
        if (!Files.exists(pathSource)) {
            System.out.println("Введена не существующая директория: " + pathSourceString);
            return;
        }

        System.out.println("Вы ввели директорию: " + pathSourceString);
        
        System.out.println("--------------------------------");
        System.out.print("Введите путь куда записать файлы: ");
        input = new Scanner(System.in, "CP1251");
        String pathDestString = input.nextLine();
        
        //проверяем, что директория существует
        Path pathDestination = Paths.get(pathDestString);
       
        if (!Files.exists(pathDestination)) {
            System.out.println("Введена не существующая директория: " + pathDestString);
            return;
        }

        System.out.println("Вы ввели директорию: " + pathDestString);
        
        
        File folder = new File(pathSource.toString());

        File[] listFiles = folder.listFiles();
        
        ArrayList<Integer> indexes = new ArrayList<>(listFiles.length);
        for(int i = 0; i < listFiles.length; i++){
            indexes.add(i);
        }
        Collections.shuffle(indexes);
        
        for(int i = 0; i < listFiles.length; i++){
            String nameFile = listFiles[indexes.get(i)].getName();
            if(listFiles[indexes.get(i)].isFile()){
                copyFile(listFiles[indexes.get(i)], new File(pathDestString + nameFile));
                System.out.println(listFiles[indexes.get(i)]);
            }
        }
        
    }
    
    public static void copyFile(File from, File to) throws IOException {
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
       int n = 0;
    }
    
}
