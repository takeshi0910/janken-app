
// 全員参加・不参加チェックボックス
document.getElementById("toggleAll").addEventListener("change", function() {
	const select = document.querySelector("select[multiple]");
	const options = select.options;
	const checked = this.checked;

	for (let opt of options) {
		opt.selected = checked;
	}

	const label = document.querySelector("label[for='toggleAll']");
	label.lastChild.textContent = checked ? " 全員不参加" : " 全員参加";
});

// gameKind -> gameMode 取得 API
async function fetchGameModes(gameKindPath) {
	try {
		const response = await axios.get("/api/game-modes", {
			params: { gameKindPath }
		});
		return response.data;
	} catch (error) {
		console.error("ゲームモード取得に失敗:", error);
		return [];
	}
}

// プルダウンの<option>タグ作成
function fillSelect(select, items) {
	select.innerHTML = "";
	items.forEach(item => {
		const opt = document.createElement("option");
		opt.value = item;
		opt.textContent = item;
		select.appendChild(opt);
	});
}

document.getElementById("gameKind").addEventListener("change", async function() {
	const selectedOption = this.options[this.selectedIndex];
	const gameKindPath = selectedOption.dataset.path;

	const modeSelect = document.getElementById("gameMode");
	const container = document.getElementById("gameModeContainer");

	// 未選択ならリセット
	if (!gameKindPath) {
		container.style.display = "none";
		modeSelect.innerHTML = "";
		return;
	}

	const gameModes = await fetchGameModes(gameKindPath);

	if (!gameModes || gameModes.length === 0) {
		container.style.display = "none";
		modeSelect.innerHTML = "";
		return;
	}

	fillSelect(modeSelect, gameModes);
	container.style.display = "block";
});