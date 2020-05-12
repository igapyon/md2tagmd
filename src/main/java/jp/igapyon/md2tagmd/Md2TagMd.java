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
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 入力で与えられたディレクトリ以下の Wiki mdファイルを入力に Indexファイルなどを生成します。
 * 
 * @author Toshiki Iga
 */
public class Md2TagMd {
    private File basedir = null;

    public Md2TagMd(File basedir) {
        this.basedir = basedir;
    }

    public void process() throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("# Wiki インデックス\n" //
                + "\n" //
                + "自動的に生成された Wiki のインデックスです。\n" //
                + "\n" //
                + "| Wiki 名   | タイトル      | タグ      |\n" //
                + "| --------- | ----------- | -------- |\n");

        List<File> fileList = Md2TagMdUtil.getFileList(basedir);
        for (File file : fileList) {
            String title = "noname";
            String tag = "";

            String[] contents = FileUtils.readFileToString(file, "UTF-8").split("\n");
            boolean isTitleFound = false;
            boolean isTagFound = false;
            for (String line : contents) {
                if (isTitleFound == false) {
                    if (line.startsWith("#")) {
                        title = line.substring(1).trim();
                        isTitleFound = true;
                    }
                }
                if (isTagFound == false) {
                    if (line.toLowerCase().startsWith("**tag**:")) {
                        tag = line.substring("**tag**:".length()).trim();
                        isTagFound = true;
                    }
                    if (line.toLowerCase().startsWith("tag:")) {
                        tag = line.substring("tag:".length()).trim();
                        isTagFound = true;
                    }
                }
            }
            builder.append("| [[" + file.getName().substring(0, file.getName().length() - ".md".length()) + "]] | "
                    + title + " | " + tag + " |\n");
        }

        FileUtils.write(new File(basedir, "Index.md"), builder.toString(), "UTF-8");
    }
}