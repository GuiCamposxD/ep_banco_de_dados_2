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
							Cadastrar Edição
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
								v-model="year"
								label="Ano"
								clearable
							/>
						</v-col>

						<v-col>
							<v-autocomplete
								v-model="event"
								label="Evento"
								return-object
								item-title="nome"
								item-value="nome"
								clearable
								:items="events"
							/>
						</v-col>

						<v-col>
							<v-text-field
								v-model="place"
								label="Local"
								clearable
							/>
						</v-col>
					</v-row>

					<v-row>
						<v-col>
							<v-text-field
								v-model="date"
								label="Data"
								:clearable="true"
							/>
						</v-col>

						<v-col>
							<v-autocomplete
								v-model="jury"
								label="Juri"
								item-title="nome_art"
								item-value="nome_art"
								clearable
								return-object
								multiple
								:items="persons"
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
                @click="handleCreateEdition"
              >
                Cadastrar Edição
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
	name: 'CreateEvent',
	emits: [
		'closeModal',
	],
	data() {
		return {
			date: '',
			event: null,
			events: [],
			jury: [],
			persons: [],
			place: '',
			shouldShowSnackBar: false,
			snackBarMessage: '',
			year: '',
		}
	},
	async mounted() {
		const eventResponse = await axios.get('/evento')
		this.events = eventResponse.data

		const personReponse = await axios.get('/pessoa')
		this.persons = personReponse.data
	},
	methods: {
		closeModal() {
			this.$emit('closeModal')
		},
		async handleCreateEdition() {
			const response = await axios.post('/edicao', {
				date: this.date,
				event: this.event,
				jury: this.jury,
				place: this.place,
				year: this.year,
			})

			if (response.status === 200) {
				this.snackbarMessage = 'Edição cadastrada com sucesso!!!'
				this.shouldShowSnackbar = true
				return
			}

			if (response.status === 500) {
				this.snackbarMessage = 'Erro ao cadsatrar Edição, verifique os campos'
				this.shouldShowSnackbar = true
			}
		},
	}
}
</script>
