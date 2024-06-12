package Mode;

import Mode.Mode.ModeType;

public class Mode {
	private static ModeType mode = ModeType.SELECT;;
	public enum ModeType{
		SELECT,
		ASSOCIATION,
		GENERALIZATION,
		COMPOSITION,
		CLASS,
		USECASE
	}
	public static synchronized void setMode(ModeType modeType) {
        mode = modeType;
    }

    public static synchronized ModeType getMode() {
        return mode;
    }
}
