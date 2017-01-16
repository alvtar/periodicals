function validateChangePassword(form) {
	if (form["new-password-1"].value != form["new-password-2"].value) {
		errorMessage(
				form["new-password-1"],
				"Значения в полях<BR>«Новый пароль»<BR>и<BR>«Новый пароль ещё раз»<BR>не совпадают");
		return false;
	}

	return validateForm(form, [ {
		id : "new-password-1",
		message : "Пароль не может быть пустым",
		checker : checkNotEmpty
	} ]);

}
