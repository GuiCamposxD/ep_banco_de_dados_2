<template>
	<v-dialog
		max-width="1250"
		:model-value="true"
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
							Cadastrar Premiação Filme
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
							<v-autocomplete
								v-model="selectedMovie"
								label="Filme"
								clearable
								item-title="titulo_original"
								item-value="titulo_original"
								return-object
								:items="movies"
							/>
						</v-col>

						<v-col>
							<v-autocomplete
								v-model="selectedAward"
								label="Premio"
								clearable
								item-title="nome"
								item-value="nome"
								return-object
								:items="awards"
							/>
						</v-col>

						<v-col>
							<v-select
								v-model="isAwarded"
								label="É premiado"
								clearable
								:items="isAwardedOptions"
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
                @click="handleCreateMovieAward"
              >
                Cadastrar Premiação para Filme
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
	name: 'CreateMovieAward',
	data() {
		return {
			awards: [],
			isAwarded: null,
			isAwardedOptions: ['Sim', 'Não'],
			movies: [],
			selectedAward: [],
			selectedMovie: null,
			shouldShowSnackBar: false,
			snackBarMessage: '',
		}
	},
	async mounted() {
		const moviesReponse = await axios.get('/filme')
		this.movies = moviesReponse.data

		const awardsReponse = await axios.get('/premio')
		this.awards = awardsReponse.data
	},
	methods: {
		closeModal() {
			this.$emit('closeModal')
		},
		async handleCreateMovieAward() {
			const response = await axios.post('/filme_premiacao', {
				movie: this.selectedMovie,
				award: this.selectedAward,
				isAwarded: this.isAwarded === 'Sim',
			})

			if (response.status === 200) {
				this.snackBarMessage = 'Premiação para filme cadastrada com sucesso!!!'
				this.shouldShowSnackBar = true
				return
			}

			if (response.status === 500) {
				this.snackBarMessage = 'Erro ao cadastrar premiação para filme, verifique os campos'
				this.shouldShowSnackBar = true
			}
		},
	}
}
</script>
