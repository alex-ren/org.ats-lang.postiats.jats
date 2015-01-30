package jats.utfpl.stfpl.pats;

public abstract class PIns implements PStat {

	private boolean m_effect;
	public PIns(boolean effect) {
		m_effect = effect;
	}
	
	public boolean hasSideEffect() {
		return m_effect;
	}
}
