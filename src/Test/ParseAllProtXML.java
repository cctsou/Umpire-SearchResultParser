/* 
 * Author: Chih-Chiang Tsou <chihchiang.tsou@gmail.com>
 *             Nesvizhskii Lab, Department of Computational Medicine and Bioinformatics, 
 *             University of Michigan, Ann Arbor
 *
 * Copyright 2014 University of Michigan, Ann Arbor, MI
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Test;

import MSUmpire.PSMDataStructure.LCMSID;
import MSUmpire.SearchResultParser.ProtXMLParser;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author Chih-Chiang Tsou
 */
public class ParseAllProtXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, XmlPullParserException, InterruptedException {
        Logger logger = Logger.getRootLogger();
        ConsoleAppender ca = new ConsoleAppender();
        ca.setThreshold(Level.INFO);
        ca.setName("ConsoleLogger_Info");
        ca.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
        ca.activateOptions();

        logger.getLoggerRepository().resetConfiguration();
        logger.addAppender(ca);

        File folder = new File("F:\\Data\\HeLa_SWATH\\");

        String fasta = "F:\\Data\\HeLa_SWATH\\MHE_UP_hu_iRT_1309.fasta";

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile() && fileEntry.getName().endsWith("prot.xml")) {

                LCMSID protID = new LCMSID(fileEntry.getAbsolutePath(),"DECOY",fasta);
                
                ProtXMLParser protxmlparser = new ProtXMLParser(protID, fileEntry.getAbsolutePath(), 0f);
                
                //float rf = protID.GetRFactor(0.2f);        
                float rf = 1f;
                protID.RemoveLowLocalPWProtein(0.2f);
                protID.FilterByProteinDecoyFDRUsingMaxIniProb(protID.DecoyTag, 0.01f / rf);
                //protID.FilterByProteinDecoyFDRUsingMaxLocalPW(ProtIDList, tandemPara.DecoyPrefix, tandemPara.ProtFDR / rf);            
//        protID.GenerateIndisProtMap();
        protID.LoadSequence();
                logger.info(fileEntry.getName() + " Protein No.:" + protID.ProteinList.size());
            }
        }
    }

}
