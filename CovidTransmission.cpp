/** 
 * Copyright (c) 2023 @rappelrx on GitHub
 *
 * This program determines total time of contact based on terminal input,
 * and determines the risk level of contracting Covid.
 * 
 * Disclaimer:
 * The information used in this program is not fact-checked.
 * Please refer to CDC [cdc.gov] for facts about Covid.
 */

#include <iostream> 
#include <cstdio> // scanf is faster than cin
#include <string>
using namespace std;

/** 
 * This struct contains the constant variables that represent
 * the various risk levels of contracting Covid, ranging from
 * "low" to "extremely high."
 */
struct CovidTransmission {
    const string EMPTY_STRING = "";
    const string LOW_RISK = "low";
    const string MED_RISK = "medium";
    const string HIGH_RISK = "high";
    const string EXT_HIGH_RISK = "extremely high";
};

// Global constants replace magic numbers
const unsigned int g_max_day = 31;
const unsigned int g_max_hour = 23;
const unsigned int g_max_min = 59;
const unsigned int g_mins_in_day = 1440;
const unsigned int g_sec_in_min = 60;
const unsigned int g_hrs_in_day = 24;
const unsigned int g_three_hours = 180;
const unsigned int g_six_hours = 360;

/**
 * Helper method for checking validity of input for day.
 * 
 * @param input The int value representation for day.
 * @return true if input is within bounds, otherwise false.
 */
bool isValidDay(int input) {
    if (input < 0 || input > g_max_day) 
        return false;
    return true;
}

/**
 * Helper method for checking validity of input for hour.
 * 
 * @param input The int value representation for hour.
 * @return true if input is within bounds, otherwise false.
 */
bool isValidHour(int input) {
    if (input < 0 || input > g_max_hour) 
        return false;
    return true;
}

/**
 * Helper method for checking validity of input for minute.
 * 
 * @param input The int value representation for minute.
 * @return true if input is within bounds, otherwise false.
 */
bool isValidMinute(int input) {
    if (input < 0 || input > g_max_min)
        return false;
    return true;
}

int main() {
    int d1, h1, m1, d2, h2, m2;
    cout << "Please enter initial day, hour, min: ";
    scanf("%d %d %d", &d1, &h1, &m1);
    cout << "Please enter ending day, hour, min: ";
    scanf("%d %d %d", &d2, &h2, &m2);

    // Check validity of inputs.
    if (!isValidDay(d1) || !isValidDay(d2)) {
        cerr << "Invalid input for day." << endl;
        return 1;
    }
    if (!isValidHour(h1) || !isValidHour(h2)) {
        cerr << "Invalid input for hour." << endl;
        return 1;
    }
    if (!isValidMinute(m1) || !isValidMinute(m2)) {
        cerr << "Invalid input for minute." << endl;
        return 1;
    }

    // Define struct to access variables for risk levels.
    struct CovidTransmission covidRisks;
    string riskLevel = covidRisks.EMPTY_STRING;

    // Determine number of minutes of contact.
    int numMinutes = (d2 - d1) * g_mins_in_day;
    if (h2 < h1) {
        numMinutes -= g_mins_in_day;
        numMinutes += (g_hrs_in_day - h1 + h2) * g_sec_in_min;
    } else {
        numMinutes += (h2 - h1) * g_sec_in_min;
    }
    if (m2 < m1) {
        numMinutes -= g_sec_in_min;
        numMinutes += g_sec_in_min - m1 + m2;
    } else {
        numMinutes += m2 - m1;
    }

    // Determine risk level of contracting Covid.
    if (numMinutes >= 0 && numMinutes <= g_sec_in_min) {
        riskLevel = covidRisks.LOW_RISK;
    } else if (numMinutes > g_sec_in_min && numMinutes <= g_three_hours) {
        riskLevel = covidRisks.MED_RISK;
    } else if (numMinutes > g_three_hours && numMinutes <= g_six_hours) {
        riskLevel = covidRisks.HIGH_RISK;
    } else if (numMinutes > g_six_hours) {
        riskLevel = covidRisks.EXT_HIGH_RISK;
    }

    // Invalid number of minutes.
    if (numMinutes < 0) {
        cerr << "Number of minutes cannot be negative." << endl;
        return 1;
    }

    cout << numMinutes << " minutes of contact puts you at "
    << riskLevel << " risk of contracting Covid." << endl;

    return 0;
}