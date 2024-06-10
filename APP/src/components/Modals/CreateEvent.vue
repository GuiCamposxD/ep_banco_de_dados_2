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
							Cadastrar Evento
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
								v-model="eventName"
								label="Nome"
								:clearable="true"
							/>
						</v-col>

						<v-col>
							<v-text-field
								v-model="nationality"
								label="Nacionalidade"
								:clearable="true"
							/>
						</v-col>
					</v-row>

					<v-row>
						<v-col>
							<v-text-field
								v-model="type"
								label="Tipo"
								:clearable="true"
							/>
						</v-col>

						<v-col>
							<v-text-field
								v-model="startYear"
								label="Ano Inicio do Evento"
								:clearable="true"
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
                @click="handleCreateEvent"
              >
                Cadastrar Evento
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
			eventName: '',
			type: '',
			nationality: '',
			startYear: '',
			snackBarMessage: '',
			shouldShowSnackBar: false,
		}
	},
	methods: {
		closeModal() {
			this.$emit('closeModal')
		},
		async handleCreateEvent() {
			const response = await axios.post('/evento', {
				eventName: this.eventName,
				type: this.type,
				nationality: this.nationality,
				startYear: this.startYear,
			})

			if (response.status === 200) {
				this.snackBarMessage = 'Evento cadastrado com sucesso'
				this.shouldShowSnackBar = true
			}

			if (response.status === 500) {
				this.snackBarMessage = 'Erro ao cadastrar usuário, verifique os campos!'
				this.shouldShowSnackBar = true
			}
		},
		closeSnackbar() {
			this.shouldShowSnackBar = false
		},
	}
}
</script>
]
