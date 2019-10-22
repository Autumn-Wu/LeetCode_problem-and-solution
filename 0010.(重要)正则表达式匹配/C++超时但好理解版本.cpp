class Solution {
public:
    bool isMatch(string s, string p) {
        if (p.empty())
            return s.empty();
        if (p==".*")
            return true;
        if ( !s.empty() && p.size()>=2 && (s.at(0)==p.at(0) || p.at(0)=='.') && p.at(1)=='*'
            && ( isMatch(s.substr(1),p) || isMatch(s.substr(1),p.substr(2)) || isMatch(s,p.substr(2))) )
            return true;
        else if ( !s.empty() && p.size()>=2 && s.at(0)!=p.at(0) && p.at(1)=='*'
                && isMatch(s,p.substr(2)) )
            return true;
        else if ( !s.empty() && (s.at(0)==p.at(0) || p.at(0)=='.') 
                && isMatch(s.substr(1),p.substr(1)) )
            return true;
        else if ( p.size()>=2 && p.at(1)=='*' 
                && isMatch(s,p.substr(2)) )
            return true;
        return false;
    }
};
//c++��ʱ�汾�������
//substr������һ���ַ�������ʱ�����
//�Ҹ��Ӷȸо�O(3^n)