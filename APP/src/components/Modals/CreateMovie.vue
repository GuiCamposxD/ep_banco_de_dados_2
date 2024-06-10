<template>
	<v-dialog
		:model-value="true"
		max-width="1000"
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
							Cadastrar Filme
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
								v-model="originalTitle"
								label="Título Original"
								:clearable="true"
							/>
						</v-col>

						<v-col>
							<v-text-field
								v-model="productionYear"
								label="Ano de Produção"
								:clearable="true"
							/>
						</v-col>

						<v-col>
							<v-text-field
								v-model="launchDate"
								label="Data de Estreia"
								:clearable="true"
							/>
						</v-col>
						<v-col>
							<v-text-field
								v-model="brazilianTitle"
								label="Título no Brasil"
								:clearable="true"
							/>
						</v-col>
					</v-row>

					<v-row>
						<v-col>
							<v-text-field
								v-model="genre"
								label="Gênero"
								:clearable="true"
							/>
						</v-col>

						<v-col>
							<v-text-field
								v-model="originalLanguage"
								label="Idioma Original"
								:clearable="true"
							/>
						</v-col>

						<v-col>
							<v-text-field
								v-model="firstYearFund"
								label="Arrecadação no Primeiro Ano"
								:clearable="true"
							/>
						</v-col>

						<v-col>
							<v-select
								v-model="type"
								label="Tipo"
								:clearable="true"
								:items="types"
							/>
						</v-col>
					</v-row>

					<v-row>
						<v-col>
							<v-text-field
								v-model="premiereLocation"
								label="Local de Estreia"
								:clearable="true"
							/>
						</v-col>

						<v-col>
							<v-autocomplete
								v-model="movieMainDirector"
								label="Diretor Principal"
								item-title="nome_art"
								item-value="nome_art"
								return-object
								:clearable="true"
								:items="directors"
							/>
						</v-col>

						<v-col>
							<v-autocomplete
								v-model="movieDirectors"
								label="Diretores"
								item-title="nome_art"
								item-value="nome_art"
								multiple
								return-object
								:clearable="true"
								:items="directors"
							/>
						</v-col>

						<v-col>
							<v-autocomplete
								v-model="movieProducers"
								label="Produtores"
								item-title="nome_art"
								item-value="nome_art"
								multiple
								return-object
								:clearable="true"
								:items="producers"
							/>
						</v-col>
					</v-row>

					<v-row>
						<v-col>
							<v-autocomplete
								v-model="movieScreenwriter"
								label="Roteiristas"
								item-title="nome_art"
								item-value="nome_art"
								multiple
								return-object
								:clearable="true"
								:items="screenwriters"
							/>
						</v-col>

						<v-col>
							<v-autocomplete
								v-model="movieMainActors"
								label="Atores (Atrizes) Principais"
								item-title="nome_art"
								item-value="nome_art"
								multiple
								return-object
								:clearable="true"
								:items="actors"
							/>
						</v-col>

						<v-col>
							<v-autocomplete
								v-model="movieActorsCast"
								label="Atores (Atrizes) Elenco"
								item-title="nome_art"
								item-value="nome_art"
								multiple
								return-object
								:clearable="true"
								:items="actors"
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
                @click="handleCreateMovie"
              >
                Cadastrar Filme
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
	name: 'CreateMovie',
	data() {
		return {
			brazilianTitle: null,
			actors: [],
			directors: [],
			firstYearFund:null,
			genre: null,
			launchDate: null,
			movieActorsCast: [],
			movieDirectors: [],
			movieMainActors: [],
			movieMainDirector: null,
			movieProducers: [],
			movieScreenwriter: [],
			originalLanguage: null,
			originalTitle: null,
			premiereLocation: null,
			producers: [],
			productionYear: null,
			screenwriters: [],
			shouldShowSnackBar: false,
			snackBarMessage: '',
			type: null,
			types:['Documentário', 'Outros'],
		}
	},
	async mounted() {
		const actorsReponse = await axios.get('/pessoa/atores')
		this.actors = actorsReponse.data

		const directorsReponse = await axios.get('/pessoa/diretores')
		this.directors = directorsReponse.data

		const producersReponse = await axios.get('/pessoa/produtores')
		this.producers = producersReponse.data

		const screenwritersReponse = await axios.get('/pessoa/roteiristas')
		this.screenwriters = screenwritersReponse.data
	},
	methods: {
		closeModal() {
			this.$emit('closeModal')
		},
		async handleCreateMovie() {
			const response = await axios.post('/filme', {
				brazilianTitle: this.brazilianTitle,
				castActors: this.movieActorsCast,
				directors: this.movieDirectors,
				firstYearFund: this.firstYearFund,
				genre: this.genre,
				launchDate: this.launchDate,
				mainActors: this.movieMainActors,
				mainDirector: this.movieMainDirector,
				originalLanguage: this.originalLanguage,
				originalTitle: this.originalTitle,
				premiereLocation: this.premiereLocation,
				producers: this.movieProducers,
				productionYear: this.productionYear,
				screenwriters: this.movieScreenwriter,
				type: this.type,
			})

			if (response.status === 200) {
				this.snackBarMessage = 'Filme cadastrado com sucesso!!!'
				this.shouldShowSnackBar = true
			}

			if (response.status === 500) {
				this.snackBarMessage = 'Erro ao cadastrar filme, verifique os campos!'
				this.shouldShowSnackBar = true
			}
		},
	}
}
</script>
