#include "database.hpp"

int main()
{
    mydatabase d("record.txt");    

    string sname,sno;
    stringstream ss;
    int opt;
    while(1){
        cout<<"##############################################################################"<<endl;
        cout<<"options:- \n\t 1 -> add entry \n\t 2 -> delete entry \n\t 3 -> exit \n";
        cin >> opt;
        cout<<"##############################################################################"<<endl;
        if(opt != 3){
            switch(opt) {
                case 1 : 
                    cout<<"##############################################################################"<<endl;
                    cout<<"Enter Name : ";
                    cin >> sname;
                    cout<<"Enter Number : ";
                    cin >>sno;
                    cout<<"##############################################################################"<<endl;
                    ss.clear();
                    ss.str(sname);
                    ss << sname << ',' << sno;
                    d.additem(ss.str());                    
                break;
                case 2 :
                    cout<<"##############################################################################"<<endl;
                    cout<<"Enter Name : ";
                    cin >> sname;
                    cout<<"Enter Number : ";
                    cin >>sno;
                    cout<<"##############################################################################"<<endl;
                    ss.clear();
                    ss.str(sname);
                    ss << sname << ',' << sno;
                    cout<<"string stream is "<<ss.str()<<endl;
                    d.removeitem(ss.str());                    
                break;
                default:
                break;
            }
        } else
        {
            break;
        }
        
    }


}