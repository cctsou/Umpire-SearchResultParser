Umpire-SearchResultParser
=========================

PepXML and ProtXML file parser with estimated FDR filtering

Umpire search result parser(version: v1.0, 2014.11)
command : java –jar –Xmx1G Umpire-SearchResultParser.jar [Options] [Combined ProtXML file] [PepXML files...]

ProtXML extension: *.prot.xml or *.ProtXML
PepXML extension: *.pep.xml or *.PepXML

Options
        -fP     Protein FDR     ex: -fP0.01 (default: 0.01, no filtering: -1)
        -fp     Peptide FDR     ex: -fp0.05 (default: 0.01, no filtering: -1)
        -d      Decoy tag prefix        ex: -dDECOY (default: rev_)
        -fa     Fasta file
        -N      Output filename
        -pt     Initial protein probability filtering threshold ex: -pt0.5 (default: 0.5, no filtering : -1)
        -rf     R factor threshold, proteins with protein probablity less than the threshold will be used to estimate the R factor
                ex: -rf0.2 (default: 0.2, do not use R factor: -1)

If the PepXML is iProphet result, the reporeted PSM probability is iProphet probability, same with peptide ion table, the maximum probability will be based on iProphet probability.
