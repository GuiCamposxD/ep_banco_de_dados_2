<template>
	<v-dialog
		:model-value="true"
		max-width="800"
		:persistent="true"
	>
		<v-card>
			<v-card-title>
				<v-container>
					<v-row>
						<v-col
							cols="11"
						>
							<h2>
							Cadastrar Especialidade
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
								v-model="specialityName"
								label="Nome"
								:clearable="true"
							/>
						</v-col>

						<v-col>
							<v-text-field
								v-model="specialityIndex"
								label="Indíce"
								return-object
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
	name: 'CreateSpeciality',
	emits: [
		'closeModal',
	],
	data() {
		return {
      specialityName: null,
      specialityIndex: null,
      shouldShowSnackBar: false,
      snackBarMessage: null,
		}
	},
	methods: {
		closeModal() {
			this.$emit('closeModal')
		},
		async handleCreateEdition() {
      try {
        await axios.post('/specialities', {
          specialityName: this.specialityName,
          index: parseInt(this.specialityIndex),
        })

        this.snackBarMessage = 'Edição cadastrada com sucesso!!!'
        this.shouldShowSnackBar = true
      } catch (e) {
        this.snackBarMessage = 'Erro ao cadsatrar Edição, verifique os campos'
        this.shouldShowSnackBar = true
      }
		},
    closeSnackbar() {
      this.shouldShowSnackBar = false
    }
	}
}
</script>
