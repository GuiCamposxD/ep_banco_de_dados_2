<template>
	<v-dialog
		:model-value="true"
		max-width="800"
		persistent
	>
		<v-card>
			<v-card-title>
				<v-container>
					<v-row>
						<v-col
							cols="11"
						>
							<h2>
							Cadastrar Prêmio
						</h2>
						</v-col>

						<v-col
							cols="1"
							justify="end"
						>
							<v-btn
								variant="text"
								density="comfortable"
								icon="mdi-close"
								@click="closeModal"
							/>
						</v-col>
					</v-row>
				</v-container>
			</v-card-title>

			<v-card-text>
				<v-container>
					<v-row>
						<v-col>
							<v-text-field
								v-model="type"
								label="Tipo"
								clearable
							/>
						</v-col>

						<v-col>
							<v-text-field
								v-model="name"
								label="Nome"
								clearable
							/>
						</v-col>

						<v-col>
							<v-autocomplete
								v-model="edition"
								label="Edição"
								clearable
								item-title="nome_evento"
								item-value="nome_evento"
								return-object=""
								:items="editions"
							/>
						</v-col>
					</v-row>

					<v-row
            justify="end"
          >
            <v-col
              cols="2"
            >
              <v-btn
                variant="flat"
                color="#FA7654"
                @click="closeModal"
              >
                Cancelar
              </v-btn>
            </v-col>

            <v-col
              cols="auto"
            >
              <v-btn
                variant="flat"
                color="#FAC95F"
                @click="handleCreateAward"
              >
                Cadastrar Prêmio
              </v-btn>
            </v-col>
          </v-row>
				</v-container>
			</v-card-text>
		</v-card>

		<v-snackbar
			v-model="shouldShowSnackBar"
			color="#FAC95F"
			elevation="24"
			:timeout="2000"
			location="center"
		>
			<p>{{ snackBarMessage }}</p>

			<template v-slot:actions>
				<v-btn
					color="pink"
					variant="text"
					@click="closeSnackbar"
				>
					Close
				</v-btn>
			</template>
		</v-snackbar>
	</v-dialog>
</template>

<script>
import axios from 'axios'

export default {
	name: 'CreateAward',
	emits: [
    'closeModal',
  ],
	data() {
		return {
			edition: null,
			editions: [],
			name: '',
			shouldShowSnackBar: false,
			snackBarMessage: '',
			type: '',
		}
	},
	async mounted() {
		const response = await axios.get('/edicao')
		this.editions = response.data
	},
	methods: {
		closeModal() {
			this.$emit('closeModal')
		},
		async handleCreateAward() {
			const response = await axios.post('/premio', {
				edition: this.edition,
				name: this.name,
				type: this.type,
			})

			console.log(response)

			if (response.status === 200) {
				this.snackBarMessage = 'Prêmio cadastrado com sucesso!!!'
				this.shouldShowSnackBar = true
				return
			}

			if (response.status === 500) {
				this.snackBarMessage = 'Erro ao cadsatrar Prêmio, verifique os campos'
				this.shouldShowSnackBar = true
			}
		},
	}
}
</script>
