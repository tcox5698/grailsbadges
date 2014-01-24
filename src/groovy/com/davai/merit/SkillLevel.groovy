def enum SkillLevel {
	_1Learner(1, "skillLevel.label.1learner"),
	_2Beginner(2, "skillLevel.label.2beginner"),
	_3Practitioner(3, "skillLevel.label.3practitioner"),
	_4Expert(4, "skillLevel.label.4expert"),
	_5Leader(5, "skillLevel.label.5leader"),
	_6Authority(6, "skillLevel.label.6authority")
	
	final int level
	final String labelKey=labelKey
	
	SkillLevel(level, labelKey) {
		this.level=level
		this.labelKey=labelKey
	}

}