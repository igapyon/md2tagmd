/*
 * Copyright 2020 Toshiki Iga
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.igapyon.md2tagmd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * Markdown ファイルの操作のためのユーティリティクラスです。
 * 
 * @author Toshiki Iga
 */
public class Md2TagMdUtil {
    /**
     * 指定されたディレクトリ以下の Markdown ファイル (.md) を一覧取得します。
     * 
     * @param basedir 入力ディレクトリ。
     * @return ファイルの一覧。
     * @throws IOException 入出力例外が発生した場合。
     */
    public static List<File> getFileList(File basedir) throws IOException {
        List<File> result = new ArrayList<>();

        Collection<File> files = FileUtils.listFiles(basedir, new String[] { "md" }, true);
        for (Iterator<File> ite = files.iterator(); ite.hasNext();) {
            File lookup = ite.next();
            result.add(lookup);
        }

        // 得られたファイル一覧をファイル名でソートします。
        Collections.sort(result, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        // ソート後のファイル一覧を返却します。
        return result;
    }
}
