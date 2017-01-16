function validateEditPublication(form) {
	return validateForm(form, [ {
		id : "issn",
		message : "Поле «Подписной индекс» должно содержать от 3 до 8 цифр",
		checker : checkIssn
	}, {
		id : "title",
		message : "Поле «Наименование» не заполнено",
		checker : checkNotEmpty
	}, {
		id : "monthCost",
		message : "Поле «Цена подписки за месяц» должно содержать цифры",
		checker : checkMonthCost
	} ]);
}

function checkIssn(value) {
	return checkRegexp(value, "^\\d{3,8}$");
}

function checkMonthCost(value) {
	return checkRegexp(value, "^[0-9]*[.]?[0-9]+$");
}
