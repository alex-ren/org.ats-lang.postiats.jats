package jats.utfpl.patcsps;

import java.net.URL;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class PATCSPSPrinter implements PNodeVisitor {

    private STGroup m_stg;

    public PATCSPSPrinter() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/patcsps/patcsps.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
    }

    public String print(PModel model) {
        ST st = (ST) model.accept(this);

        return st.render(80);
    }

    @Override
    public Object visit(PGDecVar node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PProcBranch node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PEvent node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PExpFuncCall node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PStatLocalVarDec node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PStatAssignment node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PExpAtom node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PProcAtom node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PProcCall node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PExpID node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PModel node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PGDecProc node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PProcSeq node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PExpStackOpr node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(PProcEvent node) {
        // TODO Auto-generated method stub
        return null;
    }

}
