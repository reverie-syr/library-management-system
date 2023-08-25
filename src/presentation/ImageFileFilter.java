package presentation;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/*
 * This class implements a generic file name filter that allows the listing/selection
 * of JPEG files.
 */
public class ImageFileFilter extends FileFilter implements java.io.FileFilter
{
    public boolean accept(File file)
    {
        java.util.List<String> list = java.util.List.of(
                ".png",
                ".jpg",
                ".jpeg",
                ".gif",
                ".bmp"
        );

        return file.isFile() && list.stream().anyMatch(extension -> file.getName().toLowerCase().endsWith(extension));
    }
    public String getDescription() {
        return "Image Files (*.png, *.jpg, *.jpeg, *.gif, *.bmp)";
    }

}
