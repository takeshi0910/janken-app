// リスト上の対戦ボタン押下時、出し手情報取得
document.querySelectorAll('.battle-btn').forEach(btn => {
	btn.addEventListener('click', () => {
		const roomId = btn.dataset.roomId;

		// ① モーダルを開いた瞬間にスピナーを表示
		document.getElementById('battleModalContent').innerHTML = `
				<div class="d-flex justify-content-center my-5">
					<div class="spinner-border text-primary" role="status">
						<span class="visually-hidden">Loading...</span>
					</div>
				</div>`;
		// ②出し手情報取得
		axios.get(`/janken/registeredStatus?roomId=${roomId}`)
			.then(res => {
				document.getElementById('battleModalContent').innerHTML = res.data;
			})
			.catch(err => {
				console.error("ステータス取得エラー:", err);
			});
	});
});

// 画面読み込み時のローディングアニメーション
function showLoading() {
  document.getElementById("loading").classList.remove("d-none");
}