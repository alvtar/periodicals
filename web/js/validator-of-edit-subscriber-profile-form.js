function validateEditSubscriber(form) {
	return validateForm(form, [ {
		id : "fullName",
		message : "Поле «Ф.И.О.» не заполнено",
		checker : checkNotEmpty
	}, {
		id : "zipCode",
		message : "Поле «Почтовый индекс» должно содержать 6 цифр",
		checker : checkZipCode
	}, {
		id : "address",
		message : "Поле «Адрес» не заполнено",
		checker : checkNotEmpty
	} ]);
}

function checkZipCode(value) {
	return checkRegexp(value, "^\\d{6}$");
}