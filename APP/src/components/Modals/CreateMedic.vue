<template>
  <v-dialog
    max-width="800"
    :model-value="true"
    :persistent="true"
    @click:outside="closeModal"
  >
    <v-card>
      <v-card-title>
        <v-container>
          <v-row>
            <v-col
              cols="11"
            >
              <h2>
              Cadastrar Médico
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
                v-model="crm"
                label="CRM"
                :clearable="true"
              />
            </v-col>

            <v-col>
              <v-text-field
                v-model="doctorName"
                label="Nome"
                :clearable="true"
              />
            </v-col>
          </v-row>

          <v-row>
            <v-col>
              <v-text-field
                v-model="doctorPhone"
                label="Telefone"
                :clearable="true"
              />
            </v-col>

            <v-col>
              <v-text-field
                v-model="percentage"
                label="Percentual"
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
                color="red"
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
                color="#18435A"
                @click="handleCreatePerson"
              >
                Cadastrar Médico
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
  name: 'CreateMedic',
  emits: [
    'closeModal',
  ],
  data() {
    return {
      doctorName: '',
      percentage: '',
      doctorPhone: null,
      crm: '',
      shouldShowSnackBar: false,
    }
  },
  methods: {
    closeModal() {
      this.$emit('closeModal')
    },
    async handleCreatePerson() {
      const response = await axios.post('/doctors', {
        doctorName: this.doctorName,
        percentage: this.percentage,
        doctorPhone: this.doctorPhone,
        crm: this.crm,
      })

      if (response.status === 200) {
        this.snackBarMessage = 'Médico cadastrado com sucesso'
        this.shouldShowSnackBar = true
      }

      if (response.status === 500) {
        this.shouldShowSnackBar = true
        this.snackBarMessage = 'Erro ao cadastrar médico, verifique os campos!'
      }
    },
    closeSnackbar() {
      this.shouldShowSnackBar = false
    },
  }
}
</script>
