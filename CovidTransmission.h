/** 
 * Copyright (c) 2020 @rappelrx on GitHub
 */

#ifndef COVID_TRANSMISSION_H
#define COVID_TRANSMISSION_H
#include <iostream> // allows cin/cout
#include <cstdio> // scanf is faster than cin
#include <string>
using namespace std;

/**
 * This class determines total time of contact based on user input.
 * 
 * Note: C++17 offers usage of the "inline" keyword, which allows
 * us to both define and declare static constants.
 */
class CovidTransmission {
    private:
        // Constants replace magic numbers.
        static const int MAX_DAY = 31;
        static const int MAX_HOUR = 23;
        static const int MAX_MIN = 59;
        static const int MINS_IN_DAY = 1440;
        static const int SEC_IN_MIN = 60;
        static const int HRS_IN_DAY = 24;
        static const int THREE_HOURS = 180;
        static const int SIX_HOURS = 360;
        static const inline string EMPTY_STRING = "";
        static const inline string LOW_RISK = "low";
        static const inline string MED_RISK = "medium";
        static const inline string HIGH_RISK = "high";
        static const inline string EXT_HIGH_RISK = "extremely high";
};

#endif