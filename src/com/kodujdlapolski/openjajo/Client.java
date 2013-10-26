package com.kodujdlapolski.openjajo;

import com.kodujdlapolski.openjajo.sejmometr.GminaSet;
import com.kodujdlapolski.openjajo.sejmometr.gmina.DetailedGminaData;
import com.kodujdlapolski.openjajo.sejmometr.voivodeship.DetailedVoiovodeshipData;



public interface Client {
    GminaSet getLocalization(String latitude, String longitude);

    DetailedVoiovodeshipData[] getVoivodeshipIndicator(Integer subGroup, Integer k, Integer voivodship);
    DetailedGminaData[] getGminaIndicator(Integer subGroup, Integer k, Integer gmina);
}
