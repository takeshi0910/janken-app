document.querySelectorAll('.battle-btn').forEach(btn => {
	btn.addEventListener('click', () => {
		const roomId = btn.dataset.roomId;

		axios.get(`/janken/registeredStatus?roomId=${roomId}`)
			.then(res => {
				document.getElementById('battleModalContent').innerHTML = res.data;
			})
			.catch(err => {
				console.error("ステータス取得エラー:", err);
			});
	});
});
