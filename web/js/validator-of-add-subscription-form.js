function validateAddSubscription(form) {

	var isFormValid = validateForm(form, [ {
		id : "active",
		message : "Издание недоступно для подписки",
		checker : checkActive
	} ]);

	if (!isFormValid) {
		return false;
	}

	var items = document.getElementsByTagName("input"), len, n;
	var checkedMonths = 0;
	for (n = 0, len = items.length; n < len; n += 1) {
		if (items.item(n).type === "checkbox"
				& checkRegexp(items.item(n).id, "month.+")) {
			if (items.item(n).checked == true) {
				checkedMonths++
			}
			;

		}
	}

	if (checkedMonths == 0) {

		errorMessage(form["month"], "Не выбраны месяцы для подписки");
		return false;
	}

	return true;

}

function checkActive(value) {
	return active.checked;

}
