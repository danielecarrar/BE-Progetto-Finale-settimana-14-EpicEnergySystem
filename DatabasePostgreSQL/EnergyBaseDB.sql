PGDMP     +    $                z            EnergySystem    14.1    14.1 H    D           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            E           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            F           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            G           1262    46918    EnergySystem    DATABASE     j   CREATE DATABASE "EnergySystem" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Italian_Italy.1252';
    DROP DATABASE "EnergySystem";
                postgres    false            ?            1259    59039    cliente    TABLE     ?  CREATE TABLE public.cliente (
    id bigint NOT NULL,
    cognome_contatto character varying(255),
    data_inserimento timestamp without time zone,
    data_ultimo_contatto timestamp without time zone,
    email character varying(255),
    email_contatto character varying(255),
    fatturato_annuale numeric(19,2),
    iva character varying(255),
    nome_contatto character varying(255),
    numero_contatto character varying(255),
    pec character varying(255),
    ragione_sociale character varying(255),
    telefono character varying(255),
    tipologia_cliente character varying(255),
    sede_legale_id bigint,
    sede_operativa_id bigint
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            ?            1259    59038    cliente_id_seq    SEQUENCE     w   CREATE SEQUENCE public.cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.cliente_id_seq;
       public          postgres    false    213            H           0    0    cliente_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;
          public          postgres    false    212            ?            1259    59048    comune    TABLE     q   CREATE TABLE public.comune (
    id bigint NOT NULL,
    nome character varying(255),
    provincia_id bigint
);
    DROP TABLE public.comune;
       public         heap    postgres    false            ?            1259    59047    comune_id_seq    SEQUENCE     v   CREATE SEQUENCE public.comune_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.comune_id_seq;
       public          postgres    false    215            I           0    0    comune_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.comune_id_seq OWNED BY public.comune.id;
          public          postgres    false    214            ?            1259    59055    fattura    TABLE     ?   CREATE TABLE public.fattura (
    id bigint NOT NULL,
    anno integer NOT NULL,
    data timestamp without time zone,
    importo numeric(19,2),
    numero_fattura integer NOT NULL,
    stato character varying(255),
    cliente_id bigint
);
    DROP TABLE public.fattura;
       public         heap    postgres    false            ?            1259    59054    fattura_id_seq    SEQUENCE     w   CREATE SEQUENCE public.fattura_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.fattura_id_seq;
       public          postgres    false    217            J           0    0    fattura_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.fattura_id_seq OWNED BY public.fattura.id;
          public          postgres    false    216            ?            1259    47586    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            ?            1259    59062 	   indirizzo    TABLE     ?   CREATE TABLE public.indirizzo (
    id bigint NOT NULL,
    cap bigint,
    civico integer NOT NULL,
    localita character varying(255),
    via character varying(255),
    comune_id bigint
);
    DROP TABLE public.indirizzo;
       public         heap    postgres    false            ?            1259    59061    indirizzo_id_seq    SEQUENCE     y   CREATE SEQUENCE public.indirizzo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.indirizzo_id_seq;
       public          postgres    false    219            K           0    0    indirizzo_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.indirizzo_id_seq OWNED BY public.indirizzo.id;
          public          postgres    false    218            ?            1259    59071    province    TABLE     ?   CREATE TABLE public.province (
    id bigint NOT NULL,
    nome character varying(255),
    regione character varying(255),
    sigla character varying(255)
);
    DROP TABLE public.province;
       public         heap    postgres    false            ?            1259    59070    province_id_seq    SEQUENCE     x   CREATE SEQUENCE public.province_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.province_id_seq;
       public          postgres    false    221            L           0    0    province_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.province_id_seq OWNED BY public.province.id;
          public          postgres    false    220            ?            1259    59080    role    TABLE     W   CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(255)
);
    DROP TABLE public.role;
       public         heap    postgres    false            ?            1259    59079    role_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.role_id_seq;
       public          postgres    false    223            M           0    0    role_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;
          public          postgres    false    222            ?            1259    47634    stato_fattura    TABLE     `   CREATE TABLE public.stato_fattura (
    id bigint NOT NULL,
    stato character varying(255)
);
 !   DROP TABLE public.stato_fattura;
       public         heap    postgres    false            ?            1259    47633    stato_fattura_id_seq    SEQUENCE     }   CREATE SEQUENCE public.stato_fattura_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.stato_fattura_id_seq;
       public          postgres    false    211            N           0    0    stato_fattura_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.stato_fattura_id_seq OWNED BY public.stato_fattura.id;
          public          postgres    false    210            ?            1259    59087    user_register    TABLE       CREATE TABLE public.user_register (
    id bigint NOT NULL,
    cognome character varying(255),
    email character varying(255),
    is_active boolean NOT NULL,
    nome character varying(255),
    password character varying(255),
    user_name character varying(255)
);
 !   DROP TABLE public.user_register;
       public         heap    postgres    false            ?            1259    59086    user_register_id_seq    SEQUENCE     }   CREATE SEQUENCE public.user_register_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.user_register_id_seq;
       public          postgres    false    225            O           0    0    user_register_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.user_register_id_seq OWNED BY public.user_register.id;
          public          postgres    false    224            ?            1259    59095 	   user_role    TABLE     ]   CREATE TABLE public.user_role (
    user_id bigint NOT NULL,
    role_id integer NOT NULL
);
    DROP TABLE public.user_role;
       public         heap    postgres    false            ?           2604    59042 
   cliente id    DEFAULT     h   ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);
 9   ALTER TABLE public.cliente ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    212    213            ?           2604    59051 	   comune id    DEFAULT     f   ALTER TABLE ONLY public.comune ALTER COLUMN id SET DEFAULT nextval('public.comune_id_seq'::regclass);
 8   ALTER TABLE public.comune ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215            ?           2604    59058 
   fattura id    DEFAULT     h   ALTER TABLE ONLY public.fattura ALTER COLUMN id SET DEFAULT nextval('public.fattura_id_seq'::regclass);
 9   ALTER TABLE public.fattura ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            ?           2604    59065    indirizzo id    DEFAULT     l   ALTER TABLE ONLY public.indirizzo ALTER COLUMN id SET DEFAULT nextval('public.indirizzo_id_seq'::regclass);
 ;   ALTER TABLE public.indirizzo ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218    219            ?           2604    59074    province id    DEFAULT     j   ALTER TABLE ONLY public.province ALTER COLUMN id SET DEFAULT nextval('public.province_id_seq'::regclass);
 :   ALTER TABLE public.province ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    221    221            ?           2604    59083    role id    DEFAULT     b   ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);
 6   ALTER TABLE public.role ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    223    223            ?           2604    47637    stato_fattura id    DEFAULT     t   ALTER TABLE ONLY public.stato_fattura ALTER COLUMN id SET DEFAULT nextval('public.stato_fattura_id_seq'::regclass);
 ?   ALTER TABLE public.stato_fattura ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    211    211            ?           2604    59090    user_register id    DEFAULT     t   ALTER TABLE ONLY public.user_register ALTER COLUMN id SET DEFAULT nextval('public.user_register_id_seq'::regclass);
 ?   ALTER TABLE public.user_register ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    225    225            4          0    59039    cliente 
   TABLE DATA             COPY public.cliente (id, cognome_contatto, data_inserimento, data_ultimo_contatto, email, email_contatto, fatturato_annuale, iva, nome_contatto, numero_contatto, pec, ragione_sociale, telefono, tipologia_cliente, sede_legale_id, sede_operativa_id) FROM stdin;
    public          postgres    false    213   ?Q       6          0    59048    comune 
   TABLE DATA           8   COPY public.comune (id, nome, provincia_id) FROM stdin;
    public          postgres    false    215   ?Q       8          0    59055    fattura 
   TABLE DATA           ]   COPY public.fattura (id, anno, data, importo, numero_fattura, stato, cliente_id) FROM stdin;
    public          postgres    false    217   ?Q       :          0    59062 	   indirizzo 
   TABLE DATA           N   COPY public.indirizzo (id, cap, civico, localita, via, comune_id) FROM stdin;
    public          postgres    false    219   ?Q       <          0    59071    province 
   TABLE DATA           <   COPY public.province (id, nome, regione, sigla) FROM stdin;
    public          postgres    false    221   R       >          0    59080    role 
   TABLE DATA           (   COPY public.role (id, name) FROM stdin;
    public          postgres    false    223   !R       2          0    47634    stato_fattura 
   TABLE DATA           2   COPY public.stato_fattura (id, stato) FROM stdin;
    public          postgres    false    211   RR       @          0    59087    user_register 
   TABLE DATA           a   COPY public.user_register (id, cognome, email, is_active, nome, password, user_name) FROM stdin;
    public          postgres    false    225   oR       A          0    59095 	   user_role 
   TABLE DATA           5   COPY public.user_role (user_id, role_id) FROM stdin;
    public          postgres    false    226   -S       P           0    0    cliente_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.cliente_id_seq', 1, false);
          public          postgres    false    212            Q           0    0    comune_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.comune_id_seq', 1, false);
          public          postgres    false    214            R           0    0    fattura_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.fattura_id_seq', 1, false);
          public          postgres    false    216            S           0    0    hibernate_sequence    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hibernate_sequence', 3, true);
          public          postgres    false    209            T           0    0    indirizzo_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.indirizzo_id_seq', 1, false);
          public          postgres    false    218            U           0    0    province_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.province_id_seq', 1, false);
          public          postgres    false    220            V           0    0    role_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.role_id_seq', 2, true);
          public          postgres    false    222            W           0    0    stato_fattura_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.stato_fattura_id_seq', 1, false);
          public          postgres    false    210            X           0    0    user_register_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.user_register_id_seq', 2, true);
          public          postgres    false    224            ?           2606    59046    cliente cliente_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    213            ?           2606    59053    comune comune_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.comune
    ADD CONSTRAINT comune_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.comune DROP CONSTRAINT comune_pkey;
       public            postgres    false    215            ?           2606    59060    fattura fattura_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.fattura
    ADD CONSTRAINT fattura_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.fattura DROP CONSTRAINT fattura_pkey;
       public            postgres    false    217            ?           2606    59069    indirizzo indirizzo_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.indirizzo
    ADD CONSTRAINT indirizzo_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.indirizzo DROP CONSTRAINT indirizzo_pkey;
       public            postgres    false    219            ?           2606    59078    province province_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.province
    ADD CONSTRAINT province_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.province DROP CONSTRAINT province_pkey;
       public            postgres    false    221            ?           2606    59085    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    223            ?           2606    47639     stato_fattura stato_fattura_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.stato_fattura
    ADD CONSTRAINT stato_fattura_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.stato_fattura DROP CONSTRAINT stato_fattura_pkey;
       public            postgres    false    211            ?           2606    59094     user_register user_register_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.user_register
    ADD CONSTRAINT user_register_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.user_register DROP CONSTRAINT user_register_pkey;
       public            postgres    false    225            ?           2606    59099    user_role user_role_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);
 B   ALTER TABLE ONLY public.user_role DROP CONSTRAINT user_role_pkey;
       public            postgres    false    226    226            ?           2606    59110 "   comune fk6tgu33aa5rmh8id12mbku46hq    FK CONSTRAINT     ?   ALTER TABLE ONLY public.comune
    ADD CONSTRAINT fk6tgu33aa5rmh8id12mbku46hq FOREIGN KEY (provincia_id) REFERENCES public.province(id);
 L   ALTER TABLE ONLY public.comune DROP CONSTRAINT fk6tgu33aa5rmh8id12mbku46hq;
       public          postgres    false    215    221    3223            ?           2606    59130 %   user_role fk8a4qwwbc4u7vvb8spmbl5wmyw    FK CONSTRAINT     ?   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fk8a4qwwbc4u7vvb8spmbl5wmyw FOREIGN KEY (user_id) REFERENCES public.user_register(id);
 O   ALTER TABLE ONLY public.user_role DROP CONSTRAINT fk8a4qwwbc4u7vvb8spmbl5wmyw;
       public          postgres    false    226    225    3227            ?           2606    59125 %   user_role fka68196081fvovjhkek5m97n3y    FK CONSTRAINT     ?   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES public.role(id);
 O   ALTER TABLE ONLY public.user_role DROP CONSTRAINT fka68196081fvovjhkek5m97n3y;
       public          postgres    false    223    226    3225            ?           2606    59115 #   fattura fkf55imyo58nx83x77ufdmoa7e1    FK CONSTRAINT     ?   ALTER TABLE ONLY public.fattura
    ADD CONSTRAINT fkf55imyo58nx83x77ufdmoa7e1 FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);
 M   ALTER TABLE ONLY public.fattura DROP CONSTRAINT fkf55imyo58nx83x77ufdmoa7e1;
       public          postgres    false    217    213    3215            ?           2606    59100 #   cliente fkj864ytumjy2bwgtu7jhkvsat1    FK CONSTRAINT     ?   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fkj864ytumjy2bwgtu7jhkvsat1 FOREIGN KEY (sede_legale_id) REFERENCES public.indirizzo(id);
 M   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fkj864ytumjy2bwgtu7jhkvsat1;
       public          postgres    false    3221    213    219            ?           2606    59105 #   cliente fknfenl2nw16cln6ansxx4ljx3o    FK CONSTRAINT     ?   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fknfenl2nw16cln6ansxx4ljx3o FOREIGN KEY (sede_operativa_id) REFERENCES public.indirizzo(id);
 M   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fknfenl2nw16cln6ansxx4ljx3o;
       public          postgres    false    213    3221    219            ?           2606    59120 %   indirizzo fkt8brbuq41cphbhkgrhn1oukl1    FK CONSTRAINT     ?   ALTER TABLE ONLY public.indirizzo
    ADD CONSTRAINT fkt8brbuq41cphbhkgrhn1oukl1 FOREIGN KEY (comune_id) REFERENCES public.comune(id);
 O   ALTER TABLE ONLY public.indirizzo DROP CONSTRAINT fkt8brbuq41cphbhkgrhn1oukl1;
       public          postgres    false    215    219    3217            4      x?????? ? ?      6      x?????? ? ?      8      x?????? ? ?      :      x?????? ? ?      <      x?????? ? ?      >   !   x?3???q?v?2??]|=??b???? t??      2      x?????? ? ?      @   ?   x?m͹?0 ???}f???1J@.??Kh?Q?C"O?F??????	??S??1?ش?? ,ܑ0<fЊa?bn??6jm???*?\:???:??????	F?s?ᘄ?[?/?jB????A??A{ۯs!_???jy??2i???N?B?Q????VZ|d??]???B?џA?      A      x?3?4?2?4bC?=... *     