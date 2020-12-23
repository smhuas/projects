#ifndef __DATABASE_HPP__
#define __DATABASE_HPP__
#include <iostream>
#include <fstream>
#include <unordered_map>
#include <vector>
#include <tuple>
#include <algorithm>
#include <sstream>

using namespace std;

class mydatabase {    
    ifstream infile;
    ofstream outfile;
    string dbase_file;
    vector< tuple<string,string> > vec;
public:
    mydatabase() = delete;
    mydatabase(string s);
    void additem(string s);
    void removeitem(string s);
    void printdatabase();
};

mydatabase::mydatabase(string s)
:dbase_file(s)
{
    string str;
    infile.open(dbase_file);
    while(getline(infile,str)) { 
        vec.push_back( make_tuple(str.substr(0,str.find(',')),str.substr(str.find(',')+1))  );
    }
    infile.close();
}

void mydatabase::printdatabase()
{       
   if(vec.size() != 0) {
       for(auto i:vec) {cout<< get<0>(i)<< " " << get<1>(i) <<endl;}
   } else {
       cout<<"Database is empty "<<endl;
   }   
}

void mydatabase::additem(string s)
{
    bool rep = false;           
    for(auto i:vec){
        stringstream ss;
        ss << get<0>(i)  << ',' << get<1>(i);           
        if(ss.str().compare(s) == 0){
            cout<<"add item repeated "<<endl;
            rep = true;
        }        
    }
    if(!rep){ // not repeated
        outfile.open(dbase_file);
        vec.push_back( make_tuple(s.substr(0,s.find(',')),s.substr(s.find(',')+1))  );

        for(auto i:vec) {     // updating database 
            outfile << get<0>(i) << ',' << get<1>(i)<< endl; 
        }
        outfile.close();
    } else {
        cout<< "entry repeated!! not entered in database "<< endl;
    }
}

void mydatabase::removeitem(string s)
{    
    vec.erase(   std::remove_if(
    vec.begin(), vec.end(),
    [&](const tuple<string,string>& x) -> bool{
        stringstream ss; ss << get<0>(x)  << ',' << get<1>(x);
        return (ss.str().compare(s) == 0);
    }), vec.end());

    outfile.open(dbase_file);
    for(auto i:vec) {       // updating database
        outfile << get<0>(i) << ',' << get<1>(i)<< endl; 
    }
    outfile.close();
}


#endif //__DATABASE_HPP__