package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class CharType extends ATSEltType {
    
//    public static final CharType cType = new CharType(Decorator.TYPE);
    public static final CharType cType0 = new CharType(Decorator.T0YPE);
    
    public static final int m_size = 1;

    
  private CharType(Decorator dec) {
      super(dec);
      if (dec != Decorator.T0YPE) {
          throw new Error("Wrong kind");
      }
  }
  
    @Override
    public int getSize() {
        return m_size;
    }

//	@Override
//    public CharValue createDefault() {
//	    return new CharValue(new Character(Character.MIN_VALUE));
//    }
//
//    @Override
//    public CharValue castFrom(PrimValue pv) {
//        return CharValue.castFromV(pv);


    public static Object fromString(String text) {
        return new Character(text.charAt(0));
    }
    
    @Override
    public String toString() {
        return Character.class.getSimpleName();
    }
}
