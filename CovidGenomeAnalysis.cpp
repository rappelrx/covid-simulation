/** 
 * Copyright (c) 2023 @rappelrx on GitHub
 */

#include <iostream> 
#include <cstdio> // scanf is faster than cin
#include <string>
using namespace std;
const unsigned int COVID_BASE_PAIRS = 30001;
const string EMPTY_STRING = "";

struct Purines {
    const char adenine = 'A';
    const char guanine = 'G';
};

struct Pyrimidines {
    const char thymine = 'T';
    const char cytosine = 'C';
};

struct BaseCounts {
    int numAdenines;
    int numThymines;
    int numGuanines;
    int numCytosines;
};

void resetBaseCounts() {
    struct BaseCounts baseCounts;
    baseCounts.numAdenines = 0;
    baseCounts.numThymines = 0;
    baseCounts.numGuanines = 0;
    baseCounts.numCytosines = 0;
}

string match(const string & sequence) {
    resetBaseCounts();
    struct Purines purines;
    struct Pyrimidines pyrimidines;
    struct BaseCounts baseCounts;
    string matchDNA; // matching sequence to return
    string::const_iterator letter;
    for (letter = sequence.begin(); letter != sequence.end(); letter++) {
        if (*letter == purines.adenine) {
            baseCounts.numAdenines++;
            matchDNA.push_back(pyrimidines.thymine);
        } else if (*letter == pyrimidines.thymine) {
            baseCounts.numThymines++;
            matchDNA.push_back(purines.adenine);
        } else if (*letter == purines.guanine) {
            baseCounts.numGuanines++;
            matchDNA.push_back(pyrimidines.cytosine);
        } else if (*letter == pyrimidines.cytosine) {
            baseCounts.numCytosines++;
            matchDNA.push_back(purines.guanine);
        } else {
            return EMPTY_STRING; // invalid nucleobase found
        }
    }
    return matchDNA;
}

int main() {
    cout << "Please enter a DNA sequence: " << endl;
    char inputDNA[COVID_BASE_PAIRS];
    scanf("%s", inputDNA);
    string matchDNA = match(inputDNA);
    if (matchDNA == EMPTY_STRING) {
        cerr << "Invalid nucleobase detected in your input." << endl;
        return 1;
    }
    cout << "Corresponding DNA sequence: " << matchDNA << endl;

    return 0;
}