function validateRegisterSubscriber(form) {
	if (form["password"].value != form["password2"].value) {
		errorMessage(form["password"],
				"Значения в полях<BR>«Пароль»<BR>и<BR>«Пароль ещё раз»<BR>не совпадают");
		return false;
	}

	return validateForm(form, [ {
		id : "login",
		message : "Поле «Логин» не заполнено",
		checker : checkNotEmpty
	}, {
		id : "password",
		message : "Пароль не может быть пустым",
		checker : checkNotEmpty
	}, {
		id : "fullName",
		message : "Поле «Ф.И.О.» не заполнено",
		checker : checkNotEmpty
	}, {
		id : "role",
		message : "Поле «Роль» не заполнено",
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
